package com.example.myapplication

import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val word: Word = Word(
        1,
        Languages.English,
        "a"
    )
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
