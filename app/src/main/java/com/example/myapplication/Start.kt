package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.Database.Languages
import java.util.*
import kotlin.collections.ArrayList

class Start : AppCompatActivity() {

    lateinit var simpleList: ListView
    lateinit var first:Languages
    val model: StartViewModel = StartViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        simpleList = findViewById<ListView>(R.id.simpleListView)
        model.languages.value =  Languages.values().toMutableList()
        model.languages.observe(this, Observer<List<Languages>>{ langs ->
            setAdapter(langs)
        })
        simpleList.onItemClickListener = AdapterView.OnItemClickListener( { parent:AdapterView<*>, view: View, pos:Int, id:Long -> addOnClick(pos) })
    }
    private fun setAdapter(langs:List<Languages>){
        simpleList.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,langs)
    }

    fun addOnClick(position:Int){
        if(!::first.isInitialized) {first = model.getAtIndex(position)!!;model.change(position)} else sendIntent(first.name to model.getAtIndex(position)!!.name)
    }

    fun sendIntent(pair: Pair<String,String>){
        val intent = Intent(this, FicheActivity::class.java).apply {
            putExtra("first", pair.first)
            putExtra("second",pair.second)
        }
        startActivity(intent)
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
       (application as MyApplication).appComponent.inject(this)
       //gen = FicheGenerator()
   }*/
}
