package com.example.cathaybankinterview.view.firstpage.present

import com.example.cathaybankinterview.view.firstpage.Venue
import com.example.cathaybankinterview.view.firstpage.model.IZooRepository
import com.example.cathaybankinterview.view.thirdpage.PlantDetail

class ZooPresent(
    private val viewContract: ZooContract.ViewContract,
    private val zooRepository: IZooRepository
) :
    ZooContract.IZooPresent {

    private val venueList = mutableListOf<Venue>()

    override fun reloadList() {
        zooRepository.getZooInfo(0, 10, object : IZooRepository.IOnZooResult {
            override fun onSuccess(newList: List<Venue>) {
                venueList.clear()
                venueList.addAll(newList)
                viewContract.updateList(venueList.toList())
            }

            override fun onFailure(throwable: Throwable) {
                viewContract.showErrorMessage(throwable.message ?: "error")
            }

        })
    }

    override fun loadMoreList() {
        zooRepository.getZooInfo(0, 10, object : IZooRepository.IOnZooResult {
            override fun onSuccess(newList: List<Venue>) {
                venueList.addAll(newList)
                viewContract.updateList(venueList.toList())
            }

            override fun onFailure(throwable: Throwable) {
                viewContract.showErrorMessage(throwable.message ?: "error")
            }

        })
    }

    override fun viewDetail(position: Int) : Venue{
        return venueList[position]
    }
}