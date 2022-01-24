package com.example.cathaybankinterview.view.firstpage

import java.io.Serializable

data class Venue(
    val category: String,
    val geo: String,
    val info: String,
    val memo: String,
    val name: String,
    val no: String,
    val picUrl: String,
    val id: Int,
    val webUrl : String
) : Serializable