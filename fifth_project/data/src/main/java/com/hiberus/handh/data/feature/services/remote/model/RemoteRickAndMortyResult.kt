package com.hiberus.handh.data.feature.services.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteRickAndMortyResult<T>(
    @SerializedName("info") val info: RemoteRickAndMortyInfo,
    @SerializedName("results") val results: List<T>
)
