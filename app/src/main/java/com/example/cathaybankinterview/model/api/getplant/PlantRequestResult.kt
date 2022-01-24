package com.example.cathaybankinterview.model.api.getplant

import com.google.gson.annotations.SerializedName

data class PlantRequestResult(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<PlantInfo>?,
    @SerializedName("sort")
    val sort: String?
)