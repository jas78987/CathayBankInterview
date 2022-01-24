package com.example.cathaybankinterview.view.thirdpage.present

import android.os.Bundle
import com.example.cathaybankinterview.view.thirdpage.PlantDetail

class PlantDetailContract {

    interface ViewContract{
        fun setPlantDetail(plantDetail: PlantDetail)
    }

    interface IPlantDetailPresent{
        fun loadFromArguments(plantDetail : PlantDetail?)
    }
}