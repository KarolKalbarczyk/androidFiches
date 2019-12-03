package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.myapplication.Database.Languages

const val EXCLUDED_LANG = "exclang"
const val PICKED_LANG = "picked"

class PickLanguageActivity : AppCompatActivity() {

    lateinit var list:List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_language)
        val simpleList = findViewById<ListView>(R.id.simpleListView)
        val excludedLang = intent.getStringArrayExtra(EXCLUDED_LANG) ?: arrayOf()
        list = Languages.values().map { it.name }.filter { excludedLang.contains(it)}
        simpleList.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        simpleList.onItemClickListener = AdapterView.OnItemClickListener { parent: AdapterView<*>, view: View, pos:Int, id:Long -> addOnClick(pos) }
    }

    fun addOnClick(pos:Int){
        val intent = Intent().apply { putExtra(PICKED_LANG,list.get(pos)) }
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}
