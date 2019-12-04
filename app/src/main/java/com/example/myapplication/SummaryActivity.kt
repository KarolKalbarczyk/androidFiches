package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_summary.*

const val SUMMARY_ALL = "summaryall"
const val SUMMARY_GOOD = "summarygood"
const val SUMMARY_REPEAT_ALL_CODE = 100
const val SUMMARY_REPEAT_WRONG_CODE = 101
const val SUMMARY_GO_ON_CODE = 102
const val SUMMARY_CODE = 103
const val SUMMARY_RESULT = "summresult"
class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        val all = intent.getIntExtra(SUMMARY_ALL,0)
        val good = intent.getIntExtra(SUMMARY_GOOD,0)
        val text = "You have succesfully answered $good out of $all questions"
        findViewById<TextView>(R.id.textView).apply {
            setText(text)
        }
    }

    fun clickedRepeatAll(view: View) = returnAnwer(SUMMARY_REPEAT_ALL_CODE)

    fun clickedRepeatWrong(view: View) = returnAnwer(SUMMARY_REPEAT_WRONG_CODE)

    fun clickedGoOn(view:View) = returnAnwer(SUMMARY_GO_ON_CODE)

    fun returnAnwer(code:Int){
        val intent = Intent().apply{
            putExtra(SUMMARY_RESULT,code)
        }
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}
