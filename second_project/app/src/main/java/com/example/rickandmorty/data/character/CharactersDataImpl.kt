package com.example.rickandmorty.data.character

import com.example.rickandmorty.data.character.local.CharactersLocalImpl
import com.example.rickandmorty.data.character.remote.CharactersRemoteImpl
import com.example.rickandmorty.domain.CharactersRepository
import com.example.rickandmorty.model.Character

class CharactersDataImpl(
    private val charactersLocalImpl: CharactersLocalImpl,
    private val charactersRemoteImpl: CharactersRemoteImpl
) : CharactersRepository {
    override suspend fun getCharacters(forceRemote: Boolean): List<Character> {
        val cachedCharacterList = charactersLocalImpl.getCharacters()

        if (cachedCharacterList.isNotEmpty() && !forceRemote) {
            return cachedCharacterList
        } else {
            val remoteCharacterList = charactersRemoteImpl.getCharacters()
            saveCharacters(remoteCharacterList)
            return remoteCharacterList
        }
    }

    override suspend fun getCharacter(characterId: Int): Character {
        return charactersRemoteImpl.getCharacter(characterId)
    }

    override fun saveCharacters(characters: List<Character>) {
        charactersLocalImpl.saveCharacters(characters)
    }
}