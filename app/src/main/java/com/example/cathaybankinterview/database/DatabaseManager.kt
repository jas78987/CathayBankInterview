package com.example.cathaybankinterview.database

import androidx.room.Room
import com.example.cathaybankinterview.CathyBankInterviewApplication

object DatabaseManager {

    private var appDatabase: AppDatabase? = null

    fun getInstance(): AppDatabase {
        val database = appDatabase
        return if (database == null) {
            val instance = Room.databaseBuilder(
                CathyBankInterviewApplication.getInstance(),
                AppDatabase::class.java,
                "AppDatabase"
            ).build()
            appDatabase = instance
            instance
        } else {
            database
        }
    }
}
