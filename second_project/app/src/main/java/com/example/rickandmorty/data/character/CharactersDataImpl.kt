package com.example.rickandmorty.data.character

import com.example.rickandmorty.data.character.remote.CharactersRemoteImpl
import com.example.rickandmorty.domain.CharactersRepository
import com.example.rickandmorty.model.Character

class CharactersDataImpl(
    private val charactersRemoteImpl: CharactersRemoteImpl
) : CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        return charactersRemoteImpl.getCharacters()
    }

    override suspend fun getCharacter(characterId: Int): Character {
        return charactersRemoteImpl.getCharacter(characterId)
    }
}