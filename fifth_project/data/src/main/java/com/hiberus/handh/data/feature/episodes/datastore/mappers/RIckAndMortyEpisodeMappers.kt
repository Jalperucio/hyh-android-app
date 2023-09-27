package com.hiberus.handh.data.feature.episodes.datastore.mappers

import com.hiberus.handh.data.feature.episodes.datastore.remote.model.RemoteRickAndMortyEpisode
import com.hiberus.handh.model.feature.episodes.RickAndMortyEpisode

fun RemoteRickAndMortyEpisode.toDomain(): RickAndMortyEpisode =
    RickAndMortyEpisode(
        id = this.id,
        name = this.name,
        airDate = this.airDate,
        episode = this.episode,
        characters = this.characters,
        url = this.url,
        created = this.created,
    )