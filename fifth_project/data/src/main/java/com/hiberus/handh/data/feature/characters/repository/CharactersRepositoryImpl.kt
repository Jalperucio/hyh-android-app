package com.hiberus.handh.data.feature.characters.repository

import com.hiberus.handh.data.feature.characters.factory.RickAndMortyCharacterFactory
import com.hiberus.handh.model.feature.characters.RickAndMortyCharacter
import com.hiberus.handh.model.feature.characters.enums.GenderFilter
import com.hiberus.handh.model.feature.characters.enums.StatusFilter
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val factory: RickAndMortyCharacterFactory
): CharactersRepository {
    override suspend fun getAllCharacters(page: Int): List<RickAndMortyCharacter> =
        factory.remote.getAllCharacters(page)


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