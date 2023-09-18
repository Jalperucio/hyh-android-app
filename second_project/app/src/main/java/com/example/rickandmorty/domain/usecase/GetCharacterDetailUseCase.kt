package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.CharactersRepository
import com.example.rickandmorty.model.Character

class GetCharacterDetailUseCase(
    private val charactersRepository: CharactersRepository
) {

    suspend fun execute(characterId: Int): Character {
        return charactersRepository.getCharacter(characterId)
    }

}