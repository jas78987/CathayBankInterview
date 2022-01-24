package com.example.cathaybankinterview.view.firstpage.model

import com.example.cathaybankinterview.view.firstpage.Venue

interface IZooRepository {

    interface IOnZooResult{
        fun onSuccess(newList : List<Venue>)

        fun onFailure(throwable: Throwable)
    }

    fun getZooInfo(offset: Int, limit: Int, callback: IOnZooResult)
}