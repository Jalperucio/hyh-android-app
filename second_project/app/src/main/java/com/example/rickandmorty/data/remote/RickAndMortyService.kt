package com.example.rickandmorty.data.remote

import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

    @GET("character/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Int): Character

}