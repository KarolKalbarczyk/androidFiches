package com.example.myapplication

import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordService

class Fiche(val word1: Word, val word2: Word){

    fun compare(word: Word) =
        word == word2
}

class FicheGenerator(val langs:Pair<Languages, Languages>, val limitPerRound:Int = 9999,
                     val service: WordService
){
    private var offset = 0

    fun next():List<Pair<Word, Word>>{
        val words = service.getByLanguage(langs.first,limitPerRound,offset)
        offset += limitPerRound
        val synonimes = service.getSynonimes(words,langs.second)
        val fisches = words.zip(synonimes).shuffled()
        return fisches
    }

    fun getNextRound(){

    }

}
