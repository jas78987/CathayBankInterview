package com.example.cathaybankinterview.view.thirdpage.present

import android.os.Bundle
import com.example.cathaybankinterview.view.thirdpage.PlantDetail

class PlantDetailPresent(private val viewContract: PlantDetailContract.ViewContract) : PlantDetailContract.IPlantDetailPresent {
    override fun loadFromArguments(plantDetail : PlantDetail?) {
        plantDetail?.apply {
            viewContract.setPlantDetail(this)
        }
    }
}