package com.example.rickandmorty.api

import com.example.rickandmorty.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponse>

}