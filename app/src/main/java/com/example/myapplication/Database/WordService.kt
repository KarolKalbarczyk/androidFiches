package com.example.myapplication.Database

import javax.inject.Inject
import javax.inject.Singleton

interface WordService{
    fun getByLanguage(lang: Languages, limit:Int = 99999, offset:Int = 0): List<Word>
    fun getSynonimes(words:List<Word>, lang: Languages):List<Word>
    fun getBySynonimesAndLanguages(word: Word, lang: Languages): Word?
    fun addWord(word: Word):Long
    fun addRelation(rel: WordRelation)
    fun getById(id:Int): Word?
    fun deleteAll()
    fun getAmountInLanguage(lang:Languages):Int
    fun addSynonymes(word1: Word,word2:Word)
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
        dao.nukeRelation()
        dao.nukeTable()
    }

    override fun getByLanguage(lang: Languages, limit:Int, offset:Int): List<Word> =
        dao.getByLanguage(lang.name, limit, offset)

    override fun getBySynonimesAndLanguages(word: Word, lang: Languages): Word? =
        dao.getBySynonimesAndLanguages(word.id,lang.name)

    override fun getSynonimes(words:List<Word>, lang: Languages):List<Word> =
        dao.getAllBySynonimesAndLanguages(words.map { it.id },lang.name)

    override fun addSynonymes(word1:Word,word2:Word){
        val id1 = addWord(word1)
        val id2 = addWord(word2)
        addRelation(WordRelation(id1,id2))
        addRelation(WordRelation(id2,id1))
    }

    override fun getAmountInLanguage(lang:Languages) = dao.getAmountInLanguage(lang.name)

    override fun addWord(word: Word):Long = dao.addWord(word)

    override fun addRelation(rel: WordRelation) = dao.addRelation(rel)

    override fun getById(id:Int): Word? = dao.getByIOd(id)

}
