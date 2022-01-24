package com.example.cathaybankinterview.model

import com.example.cathaybankinterview.CathyBankInterviewApplication
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.*
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object ApiManager {

    val openDataService = getRetrofit().create(OpenDataService::class.java)


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://data.taipei/")
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}