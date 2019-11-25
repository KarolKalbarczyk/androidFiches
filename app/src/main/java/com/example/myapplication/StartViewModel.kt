package com.example.myapplication

import android.annotation.TargetApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Database.Languages
import java.lang.Integer.min
@TargetApi(24)
class StartViewModel:ViewModel() {

    val languages: MutableLiveData<List<Languages>> by lazy {
        MutableLiveData<List<Languages>>()
        }

    fun getAtIndex(i:Int):Languages? =
        languages.value?.get(i)

    fun change(i:Int){
        val list = languages.value
        if (list !=null) {
            languages.value =
                list.filterIndexed {index, languages -> index!=i }
        }
    }

}