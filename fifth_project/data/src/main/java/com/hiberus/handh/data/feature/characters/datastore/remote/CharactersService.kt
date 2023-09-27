package com.hiberus.handh.data.feature.characters.datastore.remote

import com.hiberus.handh.data.feature.characters.datastore.remote.model.RemoteRickAndMortyCharacter
import com.hiberus.handh.data.feature.services.remote.RickAndMortyService
import com.hiberus.handh.data.feature.services.remote.model.RemoteRickAndMortyResult
import com.hiberus.handh.model.feature.characters.enums.GenderFilter
import com.hiberus.handh.model.feature.characters.enums.StatusFilter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService: RickAndMortyService {
    @GET("api/character")
    suspend fun getAllCharacters(@Query("page") currentPage: Int): Response<RemoteRickAndMortyResult<RemoteRickAndMortyCharacter>>

    @GET("api/character")
    suspend fun getFilteredCharacters(
        @Query("page") currentPage: Int,
        @Query("name") nameFilter: String = "",
        @Query("status") status: StatusFilter = StatusFilter.none,
        @Query("species") species: String = "",
        @Query("gender")gender: GenderFilter = GenderFilter.none,
    ): Response<RemoteRickAndMortyResult<RemoteRickAndMortyCharacter>>

    @GET("api/character/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int
    ): Response<RemoteRickAndMortyCharacter>

    @GET("api/character/{charactersId}")
    suspend fun getDiscreteCharacters(
        @Path("charactersId",encoded = false)
        charactersId: String
    ): Response<List<RemoteRickAndMortyCharacter>>
}