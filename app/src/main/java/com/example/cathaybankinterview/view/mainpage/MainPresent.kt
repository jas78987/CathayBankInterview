package com.example.cathaybankinterview.view.mainpage

import com.example.cathaybankinterview.view.firstpage.Venue
import com.example.cathaybankinterview.view.firstpage.ZooFragment
import com.example.cathaybankinterview.view.secondpage.ZooDetailFragment
import com.example.cathaybankinterview.view.thirdpage.PlantDetail
import com.example.cathaybankinterview.view.thirdpage.PlantDetailFragment

class MainPresent(
    private val viewContract: MainContract.ViewContract,
    private val modelContract: MainContract.ModelContract
) : MainContract.IMainPresent {

    private var firstFragment : ZooFragment?  = null
    private var secondFragment : ZooDetailFragment?  = null
    private var thirdFragment : PlantDetailFragment?  = null

    private var position = 0

    private var goToZooDetailListener : IGoToZooDetail = object : IGoToZooDetail{
        override fun goToZooDetailPage(venue: Venue) {
            val fragment = modelContract.getZooDetailPage(venue)
            fragment.setGoToPlantDetailListener(goToPlantDetailListener)
            position = 1
            secondFragment = fragment
            viewContract.enableNavigationButton(true)
            viewContract.changePage(fragment)
        }
    }

    private val goToPlantDetailListener = object : IGoToPlantDetail{
        override fun goToPlantDetailPage(plantDetail: PlantDetail) {
            val fragment = modelContract.getPlantDetailPage(plantDetail)
            position = 2
            thirdFragment = fragment
            viewContract.enableNavigationButton(true)
            viewContract.changePage(fragment)
        }
    }


    override fun loadHomePage() {
        val homeFragment = modelContract.getHomePage()
        homeFragment.setGoToZooDetailListener(goToZooDetailListener)
        firstFragment = homeFragment
        position = 0
        viewContract.enableNavigationButton(false)
        viewContract.changePage(homeFragment)
    }

    override fun back() {
        val fragment = when(position){
            1 -> {
                position = 0
                viewContract.enableNavigationButton(false)
                firstFragment
            }
            2 -> {
                position = 1
                viewContract.enableNavigationButton(true)
                secondFragment
            }
            else -> firstFragment
        }
        viewContract.changePage(fragment!!)

    }
}