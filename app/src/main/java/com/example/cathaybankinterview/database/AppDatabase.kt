package com.example.cathaybankinterview.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cathaybankinterview.database.dao.PlantDetailDao
import com.example.cathaybankinterview.database.entity.PlantDetailEntity

@Database(entities = [PlantDetailEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDetailDao(): PlantDetailDao
}
