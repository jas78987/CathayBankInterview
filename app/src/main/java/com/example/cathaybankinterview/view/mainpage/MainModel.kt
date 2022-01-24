package com.example.cathaybankinterview.view.mainpage

import com.example.cathaybankinterview.view.firstpage.Venue
import com.example.cathaybankinterview.view.firstpage.ZooFragment
import com.example.cathaybankinterview.view.secondpage.ZooDetailFragment
import com.example.cathaybankinterview.view.thirdpage.PlantDetail
import com.example.cathaybankinterview.view.thirdpage.PlantDetailFragment

class MainModel : MainContract.ModelContract{
    override fun getHomePage(): ZooFragment {
        return ZooFragment.newInstance()
    }

    override fun getZooDetailPage(venue: Venue): ZooDetailFragment {
        return ZooDetailFragment.newInstance(venue)
    }

    override fun getPlantDetailPage(plantDetail: PlantDetail): PlantDetailFragment {
        return PlantDetailFragment.newInstance(plantDetail)
    }
}