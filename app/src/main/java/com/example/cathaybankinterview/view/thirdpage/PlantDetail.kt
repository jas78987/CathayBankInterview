package com.example.cathaybankinterview.view.thirdpage

import java.io.Serializable

data class PlantDetail(
    /**
     * 中文名稱
     */
    val chineseName : String,

    /**
     * 英文名稱
     */
    val englishName : String,

    /**
     * 植物圖片網址
     */
    val pictureUrl : String,

    /**
     * 別名
     */
    val alias : String,

    /**
     * 簡介
     */
    val brief : String,

    /**
     * 特徵
     */
    val feature : String,

    /**
     * 功能與應用
     */
    val functionAndApplication : String,

    /**
     * 更新時間
     */
    val updateTime : String,

    /**
     * 園區位置
     */
    val locationList : List<String>
) : Serializable