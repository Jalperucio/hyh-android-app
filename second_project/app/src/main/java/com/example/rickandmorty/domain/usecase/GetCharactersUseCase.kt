package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.CharactersRepository
import com.example.rickandmorty.model.Character

class GetCharactersUseCase(
    private val charactersRepository: CharactersRepository
) {

    suspend fun execute(): List<Character> {
        return charactersRepository.getCharacters()
    }

}