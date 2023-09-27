package com.hiberus.handh.model.feature.episodes

import java.util.Date

data class RickAndMortyEpisode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: Date
)
