package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import com.example.myapplication.Database.Languages
import com.example.myapplication.Database.Word
import com.example.myapplication.Database.WordService
import javax.inject.Inject

class FicheActivity : AppCompatActivity() {

    lateinit var langs:Pair<Languages,Languages>
    lateinit var model :FicheViewModel
    @Inject
    lateinit var service:WordService
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

    fun initModel(){
        model.start(langs.first)
        model.next()
        model.currentWord.observe(this, Observer<Word>{word -> textView.setText(word.text) })
    }
}
