package com.example.myapplication

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FicheViewModel(val service: WordService) : ViewModel(){
    var i = 0
    val words: MutableLiveData<List<Word>> by lazy {
        MutableLiveData<List<Word>>()
    }

    val currentWord:MutableLiveData<Word> by lazy {
        MutableLiveData<Word>()
    }

    fun next(){
        val list = words.value
        if(list!=null) {
            currentWord.value = list[i++]
            if (i == list.size){
                nextRound()
            }
        }
    }

    fun start(lang: Languages){
        var scope = CoroutineScope(Job() + Dispatchers.IO)
        var help:List<Word>  = listOf()
        scope.launch {  service.addWord(Word(0,Languages.English,"aaa"))
            service.addWord(Word(1,Languages.English,"bbb"))
            words.value =service.getByLanguage(Languages.English)
        }
    }

    fun nextRound(){}
}