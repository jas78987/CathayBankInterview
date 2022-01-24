package com.example.cathaybankinterview.model

import com.example.cathaybankinterview.model.api.getplant.PlantInfoRequest
import com.example.cathaybankinterview.model.api.getplant.PlantRequestResult
import com.example.cathaybankinterview.model.api.getzooinfo.ZooRequestResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenDataService {

    /**
     * 取得動物園 各場館簡介
     */
    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getZooInfo(
        @Query("limit")
        limit: Int,
        @Query("offset")
        offset: Int
    ): Call<ZooRequestResult>

    /**
     * 取得動物園 植物資料
     */
    @GET("api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getPlant(
        @Query("limit")
        limit: Int,
        @Query("offset")
        offset: Int
    ) : Call<PlantInfoRequest>
}

