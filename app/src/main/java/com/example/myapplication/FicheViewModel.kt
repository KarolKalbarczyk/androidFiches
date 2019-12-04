package com.example.myapplication

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
    var endCounter:Int = 0
    val answers = mutableListOf<Answer>()
    val currentWord:MutableLiveData<Word> by lazy {
        MutableLiveData<Word>()
    }

    fun addToAnswers(ans:Boolean){
        val answer = Pair(i,ans)
        answers.add(answer)
    }


    fun generateFisches(langs:Pair<Languages, Languages>,
                        offset:Int,service: WordService,limit:Int = 9999): List<Pair<Word, Word>> {
        val words = service.getByLanguage(langs.first,limit,offset)
        val synonimes = service.getSynonimes(words,langs.second)
        val fisches = words.zip(synonimes).shuffled()
        return fisches
    }

    fun next(){
        if(!isNextRound()) {
            fiche = words[i++]
            currentWord.value = fiche.first
        }
    }

    fun repeatRound(){
        answers.clear()
        i = 0
        next()
    }

    fun repeatWrong(){
        val wrong:Set<Int> = answers.filter { it.second != true }.map { it.first }.toSet()
        words = words.filterIndexed {ind,word -> ind in wrong  }
        repeatRound()
    }

    fun getAnswer() = fiche.second.text

    fun start(lang: Pair<Languages,Languages>){
        langs = lang
        setEndCounter()
        loadWords()
    }

    private fun setEndCounter(){
        CoroutineScope(Job() + Dispatchers.IO).launch { endCounter = service.getAmountInLanguage(langs.first) }
    }

    fun loadWords(){
        var scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            service.deleteAll()
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
            Handler(Looper.getMainLooper()).post {next()}
        }
    }

    fun isNextRound() = i == words.size

    fun isEndOfGame() = offset+limit>=endCounter

    fun nextRound(){
        answers.clear()
        i = 0
        loadWords()
    }
}