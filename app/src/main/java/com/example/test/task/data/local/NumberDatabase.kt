package com.example.test.task.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.task.presentation.model.FactUi

@Database(entities = [FactUi::class], version = 2, exportSchema = false)
abstract class NumberDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao

    companion object {
        @Volatile
        private var INSTANCE: NumberDatabase? = null

        fun getDatabase(context: Context): NumberDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NumberDatabase::class.java,
                    "facts_database"
                )
                    .fallbackToDestructiveMigration() // Додано для уникнення конфліктів
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}