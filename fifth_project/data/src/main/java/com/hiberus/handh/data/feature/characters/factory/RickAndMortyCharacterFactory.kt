package com.hiberus.handh.data.feature.characters.factory

import com.hiberus.handh.data.feature.characters.datastore.interfaces.CharactersDataStore

class RickAndMortyCharacterFactory (
    val cache: CharactersDataStore,
    val remote: CharactersDataStore
)