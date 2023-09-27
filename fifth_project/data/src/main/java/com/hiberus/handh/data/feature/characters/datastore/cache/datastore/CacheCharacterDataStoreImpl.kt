package com.hiberus.handh.data.feature.characters.datastore.cache.datastore

import com.hiberus.handh.data.feature.characters.datastore.interfaces.CharactersDataStore
import com.hiberus.handh.data.feature.characters.repository.CharactersRepository
import com.hiberus.handh.model.feature.characters.RickAndMortyCharacter
import com.hiberus.handh.model.feature.characters.enums.GenderFilter
import com.hiberus.handh.model.feature.characters.enums.StatusFilter
import kotlinx.coroutines.flow.Flow

internal class CacheCharacterDataStoreImpl: CharactersDataStore {
    override suspend fun getAllCharacters(page: Int): List<RickAndMortyCharacter> {
        TODO("Not yet implemented")
    }

    override fun getFilteredCharacters(
        page: Int,
        filterByName: String,
        filterByStatus: StatusFilter,
        filterBySpecies: String,
        filterByGender: GenderFilter
    ): Flow<List<RickAndMortyCharacter>> {
        TODO("Not yet implemented")
    }

    override fun getCharacterById(characterId: Int): Flow<RickAndMortyCharacter> {
        TODO("Not yet implemented")
    }

    override fun getDiscreteCharacters(characters: List<Int>): Flow<List<RickAndMortyCharacter>> {
        TODO("Not yet implemented")
    }
}