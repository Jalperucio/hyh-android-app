package com.hiberus.handh.model.feature.characters

import com.hiberus.handh.model.feature.common.UrlObject
import java.util.Date

data class RickAndMortyCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: UrlObject,
    val location: UrlObject,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: Date
)
