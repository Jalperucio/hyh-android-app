package com.hiberus.handh.model.feature.locations

import java.util.Date

data class RickAndMortyLocation(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: Date
)
