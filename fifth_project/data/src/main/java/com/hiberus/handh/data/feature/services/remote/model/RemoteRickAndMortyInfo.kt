package com.hiberus.handh.data.feature.services.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteRickAndMortyInfo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)
