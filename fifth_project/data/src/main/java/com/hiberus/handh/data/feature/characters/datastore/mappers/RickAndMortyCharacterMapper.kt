package com.hiberus.handh.data.feature.characters.datastore.mappers

import com.hiberus.handh.data.feature.characters.datastore.remote.model.RemoteRickAndMortyCharacter
import com.hiberus.handh.model.feature.characters.RickAndMortyCharacter

fun RemoteRickAndMortyCharacter.toDomain(): RickAndMortyCharacter =
    RickAndMortyCharacter(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin.toDomain(),
        location = this.location.toDomain(),
        image = this.image,
        episode = this.episode,
        url = this.url,
        created = this.created,
    )