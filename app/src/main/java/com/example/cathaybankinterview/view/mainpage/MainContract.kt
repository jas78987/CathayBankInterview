package com.example.cathaybankinterview.view.mainpage

import androidx.fragment.app.Fragment
import com.example.cathaybankinterview.view.firstpage.Venue
import com.example.cathaybankinterview.view.firstpage.ZooFragment
import com.example.cathaybankinterview.view.secondpage.ZooDetailFragment
import com.example.cathaybankinterview.view.thirdpage.PlantDetail
import com.example.cathaybankinterview.view.thirdpage.PlantDetailFragment

class MainContract {
    interface ViewContract{
        fun changePage(fragment : Fragment)

        fun enableNavigationButton(enable : Boolean)
    }

    interface ModelContract{
        fun getHomePage() : ZooFragment

        fun getZooDetailPage(venue : Venue) : ZooDetailFragment

        fun getPlantDetailPage(plantDetail: PlantDetail) : PlantDetailFragment
    }

    interface IMainPresent{
        fun loadHomePage()

        fun back()
    }
}