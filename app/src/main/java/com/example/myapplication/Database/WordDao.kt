package com.example.myapplication.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("Select * from _Word where lang = :lang limit :offset,:limit")
    fun getByLanguage(lang:String,limit:Int = 99999,offset:Int = 0): List<Word>

    @Query("""
             Select w2.id,w2.lang,w2.text from _Word w inner join WordRelation r
             on w.id = r.fid inner join _Word w2 on w2.id = r.sid
             where w2.lang = :lang and w.id = :wordId""")
    fun getBySynonimesAndLanguages(wordId:Long,lang:String): Word?

    @Query("""
             Select w2.id,w2.lang,w2.text from _Word w inner join WordRelation r
             on w.id = r.fid inner join _Word w2 on w2.id = r.sid
             where w2.lang = :lang and w.id in (:wordId)""")
    fun getAllBySynonimesAndLanguages(wordId:List<Long>,lang:String):List<Word>

    @Query("""
        Select count() 
        from _word
        where lang = :lang
    """)
    fun getAmountInLanguage(lang:String):Int

    @Insert
    fun addWord(word: Word):Long

    @Query("DELETE FROM _word")
    fun nukeTable()

    @Query("DELETE FROM WordRelation")
    fun nukeRelation()

    @Insert
    fun addRelation(rel: WordRelation)

    @Query("Select * from _Word where id = :id")
    fun getByIOd(id:Int): Word?
}