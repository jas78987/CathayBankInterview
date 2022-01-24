package com.example.cathaybankinterview.model.api.getzooinfo

import com.google.gson.annotations.SerializedName

data class VenueInfo(
    @SerializedName("E_Category")
    val eCategory: String?,
    @SerializedName("E_Geo")
    val eGeo: String?,
    @SerializedName("E_Info")
    val eInfo: String?,
    @SerializedName("E_Memo")
    val eMemo: String?,
    @SerializedName("E_Name")
    val eName: String?,
    @SerializedName("E_no")
    val eNo: String?,
    @SerializedName("E_Pic_URL")
    val ePicURL: String?,
    @SerializedName("E_URL")
    val eURL: String?,
    @SerializedName("_id")
    val id: Int?
)