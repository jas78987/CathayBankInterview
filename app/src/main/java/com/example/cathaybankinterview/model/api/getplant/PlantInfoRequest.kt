package com.example.cathaybankinterview.model.api.getplant
import com.google.gson.annotations.SerializedName


data class PlantInfoRequest(
    @SerializedName("result")
    val plantRequestResult: PlantRequestResult?
)