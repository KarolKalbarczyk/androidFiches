package com.example.myapplication.Database

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

interface WordService{
    fun getByLanguage(lang: Languages, limit:Int = 99999, offset:Int = 0): List<Word>
    fun getSynonimes(words:List<Word>, lang: Languages):List<Word>
    fun getBySynonimesAndLanguages(word: Word, lang: Languages): Word?
    fun addWord(word: Word)
    fun addRelation(rel: WordRelation)
    fun getById(id:Int): Word?
    fun deleteAll()
}
@Singleton
class WordServiceImpl:
    WordService  {

    val dao:WordDao
    @Inject
    constructor(dao: WordDao){
        this.dao = dao
    }


    override fun deleteAll() {
        dao.nukeTable()
    }

    override fun getByLanguage(lang: Languages, limit:Int, offset:Int): List<Word> =
        dao.getByLanguage(lang.name, limit, offset)

    override fun getBySynonimesAndLanguages(word: Word, lang: Languages): Word? =
        dao.getBySynonimesAndLanguages(word.id,lang.name)

    override fun getSynonimes(words:List<Word>, lang: Languages):List<Word> =
        dao.getAllBySynonimesAndLanguages(words.map { it.id },lang.name)

    override fun addWord(word: Word) = dao.addWord(word)

    override fun addRelation(rel: WordRelation) = dao.addRelation(rel)

    override fun getById(id:Int): Word? = dao.getByIOd(id)

}
