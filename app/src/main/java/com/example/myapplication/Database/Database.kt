package com.example.myapplication.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(
    Word::class,
    WordRelation::class),version = 1)
abstract class Database :RoomDatabase(){
    abstract val wordDao: WordDao
}