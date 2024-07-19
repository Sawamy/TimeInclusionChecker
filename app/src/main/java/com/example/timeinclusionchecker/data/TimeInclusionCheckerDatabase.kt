package com.example.timeinclusionchecker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class TimeInclusionCheckerDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var Instance: TimeInclusionCheckerDatabase? = null

        fun getDatabase(context: Context): TimeInclusionCheckerDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TimeInclusionCheckerDatabase::class.java,
                    "history_database"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}