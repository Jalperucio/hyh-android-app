package com.example.rickandmorty.domain

import com.example.rickandmorty.model.Character

interface CharactersRepository {

    suspend fun getCharacters(forceRemote: Boolean): List<Character>

    suspend fun getCharacter(characterId: Int): Character

    fun saveCharacters(characters: List<Character>)

}