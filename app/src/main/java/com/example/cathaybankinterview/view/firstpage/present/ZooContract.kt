package com.example.cathaybankinterview.view.firstpage.present

import com.example.cathaybankinterview.view.firstpage.Venue

class ZooContract {
    interface ViewContract {
        fun updateList(newList : List<Venue>)

        fun showErrorMessage(message : String)

        fun showLoading(enable : Boolean)
    }

    interface IZooPresent{
        /**
         * 刷新館區簡介清單
         */
        fun reloadList()

        /**
         * 讀取更多館區簡介清單
         */
        fun loadMoreList()

        /**
         * 前往觀看館區詳細資訊
         */
        fun viewDetail(position: Int) : Venue
    }
}