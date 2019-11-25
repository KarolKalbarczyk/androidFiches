package com.example.myapplication

import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import org.junit.Assert
import org.junit.Test

class FicheTest {

    @Test
    fun testWordComparison(): Unit {
        val word = Word(
            0,
            Languages.English,
            "text"
        )
        val word2 = Word(
            1,
            Languages.Polish,
            "tekst"
        )
        val fiche = Fiche(word,word2)
        val word3 = Word(
            2,
            Languages.Polish,
            "tekst"
        )
        Assert.assertTrue(fiche.compare(word3))
    }
}