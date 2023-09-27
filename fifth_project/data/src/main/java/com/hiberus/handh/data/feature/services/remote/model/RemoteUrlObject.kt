package com.hiberus.handh.data.feature.services.remote.model

import com.google.gson.annotations.SerializedName
import com.hiberus.handh.model.feature.common.UrlObject

data class RemoteUrlObject(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
){
    fun toDomain(): UrlObject = UrlObject(
        name = this.name,
        url = this.url
    )
}
