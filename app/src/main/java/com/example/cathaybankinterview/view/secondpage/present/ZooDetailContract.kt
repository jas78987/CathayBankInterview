package com.example.cathaybankinterview.view.secondpage.present

import com.example.cathaybankinterview.view.firstpage.Venue
import com.example.cathaybankinterview.view.thirdpage.PlantDetail

class ZooDetailContract {

    interface ViewContract{
        fun displayDetail(venue: Venue)

        fun openInWeb(url : String)

        fun updateList(newList : List<PlantDetail>)

        fun showErrorMessage(throwable: Throwable)
    }

    interface IZooDetailPresent {

        /*
         * 讀取詳細的
         */
        fun loadDetail(venue: Venue)

        fun backPage()

        fun viewPlantDetail(position : Int) : PlantDetail

        fun viewInWeb()
    }
}