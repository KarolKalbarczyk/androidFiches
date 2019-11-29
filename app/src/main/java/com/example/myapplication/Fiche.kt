package com.example.myapplication

import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordService

class Fiche(val word1: Word, val word2: Word){

    fun compare(word: Word) =
        word == word2
}

fun generateFisches(langs:Pair<Languages, Languages>,
                    offset:Int,service: WordService,limit:Int = 9999):
        List<Pair<Word, Word>> {
    val words = service.getByLanguage(langs.first,limit,offset)
    val synonimes = service.getSynonimes(words,langs.second)
    val fisches = words.zip(synonimes).shuffled()
    return fisches
}
