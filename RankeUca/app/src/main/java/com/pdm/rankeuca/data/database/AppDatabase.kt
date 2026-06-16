package com.pdm.rankeuca.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdm.rankeuca.data.database.dao.OptionDao
import com.pdm.rankeuca.data.database.entities.OptionEntity
import com.pdm.rankeuca.data.database.dao.QuestionDao
import com.pdm.rankeuca.data.database.entities.QuestionEntity



@Database(
    entities = [QuestionEntity::class, OptionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun optionDao(): OptionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabase::class.java,
                    name = "rankeuca_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}