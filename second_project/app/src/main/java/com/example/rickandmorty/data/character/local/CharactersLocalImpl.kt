package com.example.rickandmorty.data.character.local

import com.example.rickandmorty.data.local.MemoryCache
import com.example.rickandmorty.model.Character

class CharactersLocalImpl(
    private val memoryCache: MemoryCache
) {
    fun getCharacters(): List<Character> {
        return memoryCache.characterList.orEmpty()
    }

    fun saveCharacters(characters: List<Character>) {
        memoryCache.characterList = characters
    }
}