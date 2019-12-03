package com.example.myapplication

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.Database.*

@RunWith(AndroidJUnit4::class)
class DAtabaseUnitTest {
    lateinit var dao: WordDao
    lateinit var db: Database

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, Database::class.java).build()
        dao = db.wordDao
    }

   /* @Test
    fun write(){
        val word = Word(
            1,
            Languages.English,
            "A"
        )
        val word2 = Word(
            2,
            Languages.Polish,
            "B"
        )
        val word3 = Word(
            3,
            Languages.English,
            "C"
        )
        val word4 = Word(
            4,
            Languages.Polish,
            "D"
        )
        dao.addWord(word)
        dao.addWord(word2)
        dao.addWord(word3)
        dao.addWord(word4)
        val rel = WordRelation(1, 2)
        val rel2 = WordRelation(3, 4)
        dao.addRelation(rel)
        dao.addRelation(rel2)
        val wr = dao.getAllBySynonimesAndLanguages(listOf(1,3),"Polish")
        println(wr)
    }
    /*@After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }*/*/

    }
