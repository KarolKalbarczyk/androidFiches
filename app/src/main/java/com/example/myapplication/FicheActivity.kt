package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordService
import javax.inject.Inject

const val ANSWER_RESULT = "answer-bool"

class FicheActivity : AppCompatActivity() {

    val ANSWER_CODE = 1
    @Inject
    lateinit var service:WordService
    lateinit var langs:Pair<Languages,Languages>
    lateinit var model :FicheViewModel
    lateinit var textView: AppCompatTextView
    var roundLimit = 9999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiche)
        langs = Pair(Languages.valueOf(intent.getStringExtra("first")),Languages.valueOf(intent.getStringExtra("second")))
        (application as MyApplication).appComponent.inject(this)
        textView = findViewById(R.id.tekst)
        model = FicheViewModel(service,roundLimit)
        initModel()
    }

    fun answer(view:View){
        val intent = Intent(this,AnswerActivity::class.java).apply {
            putExtra("answer",model.getAnswer())
        }
        startActivityForResult(intent, ANSWER_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ANSWER_CODE && resultCode == Activity.RESULT_OK && data != null){
            model.addToAnswers(data.getBooleanExtra(ANSWER_RESULT,true))
            if (model.isNextRound()) startEndOfRound()
            else model.next()
        }
        if (requestCode == SUMMARY_CODE && data != null) {
            endRound(data.getIntExtra(SUMMARY_RESULT,-1))
        }
    }

    fun endRound(code:Int){
        when (code) {
            SUMMARY_GO_ON_CODE -> if(!model.isEndOfGame()) model.nextRound() else end()
            SUMMARY_REPEAT_ALL_CODE -> model.repeatRound()
            SUMMARY_REPEAT_WRONG_CODE -> model.repeatWrong()
            else -> startEndOfRound()
        }
    }

    fun end(){
        print("AAAAAAAAAAAAAAAAAA")
    }

    fun startEndOfRound(){
        val intent = Intent(this,SummaryActivity::class.java).apply {
            putExtra(SUMMARY_ALL,roundLimit)
            putExtra(SUMMARY_GOOD,model.answers.count { it.second == true })
        }
        startActivityForResult(intent, SUMMARY_CODE)
    }

    fun initModel(){
        model.start(langs)
        model.currentWord.observe(this, Observer<Word>{word -> textView.setText(word.text) })
    }
}
