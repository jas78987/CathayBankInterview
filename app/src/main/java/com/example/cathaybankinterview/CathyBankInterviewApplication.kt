package com.example.cathaybankinterview

import android.app.Application

class CathyBankInterviewApplication : Application(){
    companion object{

        fun getInstance() : CathyBankInterviewApplication{
            return instance
        }

        private lateinit var instance : CathyBankInterviewApplication
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}