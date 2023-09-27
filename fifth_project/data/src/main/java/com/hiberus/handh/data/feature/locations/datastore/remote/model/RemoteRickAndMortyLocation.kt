package com.hiberus.handh.data.feature.locations.datastore.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RemoteRickAndMortyLocation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("residents")
    val residents: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: Date
)
