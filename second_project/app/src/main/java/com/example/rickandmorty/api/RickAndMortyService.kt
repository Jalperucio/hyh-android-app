package com.example.rickandmorty.api

import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponse>

    @GET("character/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Int): Response<Character>

}