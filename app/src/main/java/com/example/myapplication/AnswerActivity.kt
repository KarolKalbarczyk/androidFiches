package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class AnswerActivity : AppCompatActivity() {

    lateinit var text:String
    lateinit var textView: AppCompatTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        val textView  = findViewById(R.id.tekst) as TextView
        textView.setText(intent.getStringExtra("answer"))
    }

    fun clickedYes(view: View) = returnAnwer(true)

    fun clickedNo(view: View) = returnAnwer(false)

    fun returnAnwer(bool:Boolean){
        val intent = Intent().apply{
            putExtra(ANSWER_RESULT,bool)
        }
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}
