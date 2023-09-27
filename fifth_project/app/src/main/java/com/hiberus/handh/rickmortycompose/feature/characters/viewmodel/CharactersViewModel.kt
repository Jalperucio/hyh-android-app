package com.hiberus.handh.rickmortycompose.feature.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hiberus.handh.domain.feature.characters.AllCharactersUseCase
import com.hiberus.handh.model.feature.characters.RickAndMortyCharacter
import kotlinx.coroutines.flow.Flow

open class CharactersViewModel(
    allCharactersUseCase: AllCharactersUseCase
): ViewModel() {
    val allCharacters: Flow<PagingData<RickAndMortyCharacter>> = allCharactersUseCase(20)
}