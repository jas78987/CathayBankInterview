package com.example.cathaybankinterview.model.api.getzooinfo

import com.google.gson.annotations.SerializedName

data class RequestResult(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<VenueInfo>?,
    @SerializedName("sort")
    val sort: String?
)