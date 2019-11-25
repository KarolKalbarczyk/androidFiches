package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordService

class FicheViewModel() : ViewModel(){
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

    fun nextRound(){}
}