package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordService
import javax.inject.Inject

const val ANSWER_RESULT = "answer-bool"

class FicheActivity : AppCompatActivity() {

    val answerCode = 1
    @Inject
    lateinit var service:WordService
    lateinit var langs:Pair<Languages,Languages>
    lateinit var model :FicheViewModel
    lateinit var textView: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiche)
        langs = Pair(Languages.valueOf(intent.getStringExtra("first")),Languages.valueOf(intent.getStringExtra("second")))
        (application as MyApplication).appComponent.inject(this)
        textView = findViewById(R.id.tekst)
        model = FicheViewModel(service)
        initModel()
    }

    fun answer(view:View){
        val intent = Intent(this,AnswerActivity::class.java).apply {
            putExtra("answer",model.getAnswer())
        }
        startActivityForResult(intent, answerCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == answerCode && resultCode == Activity.RESULT_OK && data != null){
            model.addToAnswers(data.getBooleanExtra(
                ANSWER_RESULT,true))
            model.next()
        }
    }

    fun initModel(){
        model.start(langs)
        model.next()
        model.currentWord.observe(this, Observer<Word>{word -> textView.setText(word.text) })
    }
}
