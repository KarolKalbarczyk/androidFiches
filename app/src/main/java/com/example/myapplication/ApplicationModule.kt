package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.myapplication.Database.Database
import com.example.myapplication.Database.WordDao
import com.example.myapplication.Database.WordService
import com.example.myapplication.Database.WordServiceImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val context:Context) {

    @Provides
    fun appContext() = context

    @Provides
    @Singleton
    fun getDatabase(context:Context): Database {
        val db = Room.databaseBuilder(
            context,
            Database::class.java, "database-name"
        ).build()
        return db
    }

    @Provides
    @Singleton
    fun getWordDao(db: Database): WordDao {
        return db.wordDao
    }

    @Provides
    fun getWordService(ser:WordServiceImpl): WordService =
        ser
}
@Singleton
@Component(modules =  [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(myApplication: Application)

    fun inject(activity: Start)

    fun inject(act:FicheActivity)

}