package com.example.myapplication.Database

import androidx.room.*

@Entity(tableName = "_word")
data class Word(@PrimaryKey var id:Int = 0,
                @Ignore var language: Languages = Languages.English,
                @ColumnInfo(name = "text") var text:String = ""){
    @ColumnInfo(name = "lang")
    var lang = language.name

    override fun equals(other: Any?): Boolean =
        if(other != null && other is Word) other.text == this.text
        else false


}

@Entity(primaryKeys = arrayOf("fid","sid"),
        foreignKeys = arrayOf(
            ForeignKey(entity = Word::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("fid")
                ),
            ForeignKey(entity = Word::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("sid")
            )
        ))
class WordRelation(val fid:Int,val sid:Int)

enum class Languages {
    English,Polish,German
}