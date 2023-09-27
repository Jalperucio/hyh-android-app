package com.hiberus.handh.data.feature.characters.repository

import com.hiberus.handh.model.feature.characters.RickAndMortyCharacter
import com.hiberus.handh.model.feature.characters.enums.GenderFilter
import com.hiberus.handh.model.feature.characters.enums.StatusFilter
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(page: Int): List<RickAndMortyCharacter>

    fun getFilteredCharacters(
        page: Int,
        filterByName: String,
        filterByStatus: StatusFilter,
        filterBySpecies: String,
        filterByGender: GenderFilter
    ): Flow<List<RickAndMortyCharacter>>

    fun getCharacterById(characterId: Int): Flow<RickAndMortyCharacter>

    fun getDiscreteCharacters(characters: List<Int>): Flow<List<RickAndMortyCharacter>>
}