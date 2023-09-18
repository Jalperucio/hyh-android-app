package com.example.rickandmorty.data.character.remote

import com.example.rickandmorty.data.remote.RickAndMortyService
import com.example.rickandmorty.model.Character

class CharactersRemoteImpl(
    private val rickAndMortyService: RickAndMortyService
) {

    suspend fun getCharacters(): List<Character> {
        return rickAndMortyService.getCharacters().characters
    }

    suspend fun getCharacter(characterId: Int): Character {
        return rickAndMortyService.getCharacter(characterId)
    }

}