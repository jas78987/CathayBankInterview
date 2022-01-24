package com.example.cathaybankinterview.model.api.getzooinfo

import com.google.gson.annotations.SerializedName

data class ZooRequestResult(
    @SerializedName("result")
    val requestResult: RequestResult?
)