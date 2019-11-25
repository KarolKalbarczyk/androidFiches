package com.example.myapplication.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("Select * from _Word where lang = :lang limit :offset,:limit")
    fun getByLanguage(lang:String,limit:Int = 99999,offset:Int = 0):List<Word>

    @Query("""
             Select w2.id,w2.lang,w2.text from _Word w inner join WordRelation r
             on w.id = r.fid inner join _Word w2 on w2.id = r.sid
             where w2.lang = :lang and w.id = :wordId""")
    fun getBySynonimesAndLanguages(wordId:Int,lang:String): Word?

    @Query("""
             Select w2.id,w2.lang,w2.text from _Word w inner join WordRelation r
             on w.id = r.fid inner join _Word w2 on w2.id = r.sid
             where w2.lang = :lang and w.id in (:wordId)""")
    fun getAllBySynonimesAndLanguages(wordId:List<Int>,lang:String):List<Word>

    @Insert
    fun addWord(word: Word)

    @Insert
    fun addRelation(rel: WordRelation)

    @Query("Select * from _Word where id = :id")
    fun getByIOd(id:Int): Word?
}