package com.example.myapplication

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordRelation
import com.example.myapplication.Database.WordService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

typealias Answer = Pair<Int,Boolean>


class FicheViewModel(val service: WordService, val limit:Int) : ViewModel(){
    var i = 0
    var offset = 0
    lateinit var words: List<Pair<Word,Word>>
    lateinit var fiche:Pair<Word,Word>
    lateinit var langs:Pair<Languages,Languages>
    val answers = mutableListOf<Answer>()
    val currentWord:MutableLiveData<Word> by lazy {
        MutableLiveData<Word>()
    }

    fun addToAnswers(ans:Boolean){
        val answer = Pair(i,ans)
        answers.add(answer)
    }

    fun next(){
        val list = words
        if(list!=null) {
            fiche = list[i++]
            currentWord.value = fiche.first
            nextRound()
        }
    }

    fun getAnswer() = fiche.second.text

    fun start(lang: Pair<Languages,Languages>){
        langs = lang
        loadWords()
        while(!::words.isInitialized){}
    }

    fun loadWords(){
        var scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            //service.deleteAll()
            /*service.addWord(Word(0,Languages.English,"aaa"))
            service.addWord(Word(1,Languages.Polish,"bbb"))
            service.addWord(Word(2,Languages.English,"ccc"))
            service.addWord(Word(3,Languages.Polish,"ddd"))
            service.addRelation(WordRelation(0,1))
            service.addRelation(WordRelation(2,3))*/
            if (offset == 0 ) {
                service.addSynonymes(
                    Word(Languages.English, "sand"),
                    Word(Languages.Polish, "piasek")
                )
                service.addSynonymes(
                    Word(Languages.English, "exit"),
                    Word(Languages.Polish, "wyjscie")
                )
                service.addSynonymes(
                    Word(Languages.English, "english"),
                    Word(Languages.Polish, "ang")
                )
                service.addSynonymes(
                    Word(Languages.English, "polish"),
                    Word(Languages.Polish, "pols")
                )
                service.addSynonymes(
                    Word(Languages.English, "name"),
                    Word(Languages.Polish, "imie")
                )
                service.addSynonymes(
                    Word(Languages.English, "word"),
                    Word(Languages.Polish, "slowo")
                )
            }
            words = generateFisches(langs,offset,service,limit)
            offset += limit
        }
    }

    fun nextRound(){
        if (i == words.size){
            loadWords()
            i = 0
            next()
        }
    }
}