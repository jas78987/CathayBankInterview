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

    private var isReachEnd = false

    private val fetchCount = 10

    override fun reloadList() {
        viewContract.showLoading(true)
        isReachEnd = false
        zooRepository.getZooInfo(0, fetchCount, object : IZooRepository.IOnZooResult {
            override fun onSuccess(newList: List<Venue>) {
                if (newList.size < fetchCount){
                    isReachEnd = true
                }
                venueList.clear()
                venueList.addAll(newList)
                viewContract.updateList(venueList.toList())
                viewContract.showLoading(false)
            }

            override fun onFailure(throwable: Throwable) {
                viewContract.showErrorMessage(throwable.message ?: "error")
                viewContract.showLoading(false)
            }

        })
    }

    override fun loadMoreList() {
        if (isReachEnd){
            return
        }
        viewContract.showLoading(true)
        zooRepository.getZooInfo(venueList.size, fetchCount, object : IZooRepository.IOnZooResult {
            override fun onSuccess(newList: List<Venue>) {
                if (newList.size < fetchCount){
                    isReachEnd = true
                }
                venueList.addAll(newList)
                viewContract.updateList(venueList.toList())
                viewContract.showLoading(false)
            }

            override fun onFailure(throwable: Throwable) {
                viewContract.showErrorMessage(throwable.message ?: "error")
                viewContract.showLoading(false)
            }

        })
    }

    override fun viewDetail(position: Int) : Venue{
        return venueList[position]
    }
}