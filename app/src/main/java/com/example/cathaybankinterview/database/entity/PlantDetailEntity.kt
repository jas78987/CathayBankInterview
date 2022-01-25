package com.example.cathaybankinterview.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlantDetailEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val chineseName: String,
    val englishName: String,
    val pictureUrl: String,
    val alias: String,
    val brief: String,
    val feature: String,
    val functionAndApplication: String,
    val updateTime: String,
    val locationList: String
)
