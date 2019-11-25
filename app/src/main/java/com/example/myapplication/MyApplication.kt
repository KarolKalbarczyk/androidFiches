package com.example.myapplication

import android.app.Application
import android.content.res.Configuration
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class MyApplication : Application() {

    val appComponent : ApplicationComponent by  lazy { DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build() }

   /* fun getApplicationComponent(): ApplicationComponent {
        if (appComponent == null) {
            appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        }
        return mApplicationComponent
    }*/
}