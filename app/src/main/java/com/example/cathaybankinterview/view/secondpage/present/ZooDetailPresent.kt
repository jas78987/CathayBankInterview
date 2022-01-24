package com.example.cathaybankinterview.view.secondpage.present

import com.example.cathaybankinterview.view.firstpage.Venue
import com.example.cathaybankinterview.view.secondpage.model.IZooDetailRepository
import com.example.cathaybankinterview.view.thirdpage.PlantDetail

class ZooDetailPresent(
    private val viewContract: ZooDetailContract.ViewContract,
    private val zooDetailRepository: IZooDetailRepository
) :
    ZooDetailContract.IZooDetailPresent {

    private lateinit var venueInfo: Venue

    private val plantDetailList = mutableListOf<PlantDetail>()

    override fun loadDetail(venue: Venue) {
        venueInfo = venue
        viewContract.displayDetail(venue)
        zooDetailRepository.getRelativePlant(venue.name,object : IZooDetailRepository.IOnPlantDetailResult{
            override fun onSuccess(newList: List<PlantDetail>) {
                plantDetailList.addAll(newList)
                viewContract.updateList(plantDetailList)
            }

            override fun onFailure(throwable: Throwable) {
                viewContract.showErrorMessage(throwable)
            }

        })
    }

    override fun backPage() {

    }

    override fun viewPlantDetail(position : Int) : PlantDetail{
        return plantDetailList[position]
    }

    override fun viewInWeb() {
        viewContract.openInWeb(venueInfo.webUrl)
    }
}