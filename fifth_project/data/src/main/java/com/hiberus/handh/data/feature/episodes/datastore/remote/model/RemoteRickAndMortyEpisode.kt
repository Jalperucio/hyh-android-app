package com.hiberus.handh.data.feature.episodes.datastore.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RemoteRickAndMortyEpisode(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: Date
)
