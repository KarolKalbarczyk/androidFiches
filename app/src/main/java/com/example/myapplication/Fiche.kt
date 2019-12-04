package com.example.myapplication

import androidx.lifecycle.Observer
import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordService

class Fiche(val word1: Word, val word2: Word){

    fun compare(word: Word) =
        word == word2
}

