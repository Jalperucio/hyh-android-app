package com.hiberus.handh.data.feature.characters.datastore.remote.datastore

import com.hiberus.handh.data.feature.characters.datastore.interfaces.CharactersDataStore
import com.hiberus.handh.data.feature.characters.datastore.mappers.toDomain
import com.hiberus.handh.data.feature.characters.datastore.remote.CharactersService
import com.hiberus.handh.data.feature.characters.datastore.remote.model.RemoteRickAndMortyCharacter
import com.hiberus.handh.model.feature.characters.RickAndMortyCharacter
import com.hiberus.handh.model.feature.characters.enums.GenderFilter
import com.hiberus.handh.model.feature.characters.enums.StatusFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

internal class RemoteCharacterDataStoreImpl(
    private val charactersService: CharactersService
): CharactersDataStore {
    override suspend fun getAllCharacters(page: Int): List<RickAndMortyCharacter> {
        val result = this@RemoteCharacterDataStoreImpl.charactersService.getAllCharacters(page)

        if (result.isSuccessful)
            return (result.body()?.results?.map {character ->
                character.toDomain()
            } ?: emptyList())

        return emptyList()
    }

    override fun getFilteredCharacters(
        page: Int,
        filterByName: String,
        filterByStatus: StatusFilter,
        filterBySpecies: String,
        filterByGender: GenderFilter
    ): Flow<List<RickAndMortyCharacter>> = channelFlow {
        val result = this@RemoteCharacterDataStoreImpl.charactersService.getFilteredCharacters(
            currentPage = page,
            nameFilter = filterByName,
            status = filterByStatus,
            species = filterBySpecies,
            gender = filterByGender
        )

        if (result.isSuccessful)
            send(result.body()?.results?.map {character ->
                character.toDomain()
            } ?: emptyList())
    }

    override fun getCharacterById(characterId: Int): Flow<RickAndMortyCharacter> = channelFlow {
        val result = this@RemoteCharacterDataStoreImpl.charactersService.getCharacterById(characterId)

        if (result.isSuccessful && result.body() is RemoteRickAndMortyCharacter)
            send(result.body()!!.toDomain())
    }

    override fun getDiscreteCharacters(characters: List<Int>): Flow<List<RickAndMortyCharacter>> = channelFlow {
        val result = this@RemoteCharacterDataStoreImpl.charactersService.getDiscreteCharacters(
            charactersId = characters.joinToString(",")
        )

        if (result.isSuccessful)
            send(result.body()?.map {character ->
                character.toDomain()
            } ?: emptyList())
    }
}