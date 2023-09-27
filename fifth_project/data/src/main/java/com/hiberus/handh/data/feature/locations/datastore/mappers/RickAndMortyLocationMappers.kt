package com.hiberus.handh.data.feature.locations.datastore.mappers

import com.hiberus.handh.data.feature.locations.datastore.remote.model.RemoteRickAndMortyLocation
import com.hiberus.handh.model.feature.locations.RickAndMortyLocation

fun RemoteRickAndMortyLocation.toDomain(): RickAndMortyLocation =
    RickAndMortyLocation(
        id = this.id,
        name = this.name,
        type = this.type,
        dimension = this.dimension,
        residents = this.residents,
        url = this.url,
        created = this.created,
    )