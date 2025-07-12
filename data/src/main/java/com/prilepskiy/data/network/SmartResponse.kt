package com.prilepskiy.data.network

import com.google.gson.annotations.SerializedName

data class SmartResponse <T>(
    @SerializedName("results")
    val result: List<T>,
    @SerializedName("info")
    val info:Info
){
    data class Info(
        @SerializedName("pages")
        val pages:Int,
        @SerializedName("next")
        val next:String?,
        @SerializedName("prev")
        val prev:String
    )
}