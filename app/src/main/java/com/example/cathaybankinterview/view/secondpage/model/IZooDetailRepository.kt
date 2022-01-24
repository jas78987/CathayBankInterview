package com.example.cathaybankinterview.view.secondpage.model

import com.example.cathaybankinterview.view.thirdpage.PlantDetail

interface IZooDetailRepository {

    interface IOnPlantDetailResult{
        fun onSuccess(newList : List<PlantDetail>)

        fun onFailure(throwable: Throwable)
    }

    fun getRelativePlant(placeName: String,callback : IOnPlantDetailResult)
}