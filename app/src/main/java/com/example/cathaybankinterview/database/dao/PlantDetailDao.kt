package com.example.cathaybankinterview.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cathaybankinterview.database.entity.PlantDetailEntity

@Dao
interface PlantDetailDao{
    @Query("SELECT * FROM PlantDetailEntity")
    fun getAll(): List<PlantDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(newList : List<PlantDetailEntity>)

}
