package com.example.rickandmorty.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.usecase.GetCharacterDetailUseCase
import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias CharacterListState = ResourceState<List<Character>>
typealias CharacterDetailState = ResourceState<Character>

class CharactersViewModel(
    private val charactersUseCase: GetCharactersUseCase,
    private val characterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val characterMutableLiveData = MutableLiveData<CharacterListState>()
    private val characterDetailMutableLiveData = MutableLiveData<CharacterDetailState>()

    fun getCharacterLiveData(): LiveData<CharacterListState> {
        return characterMutableLiveData
    }

    fun getCharacterDetailLiveData(): LiveData<CharacterDetailState> {
        return characterDetailMutableLiveData
    }

    fun fetchCharacters() {
        characterMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = charactersUseCase.execute(forceRemote = true)

                withContext(Dispatchers.Main) {
                    characterMutableLiveData.value = ResourceState.Success(data)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    characterMutableLiveData.value =
                        ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }

    fun fetchCharacter(characterId: Int) {
        characterDetailMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = characterDetailUseCase.execute(characterId)

                withContext(Dispatchers.Main) {
                    characterDetailMutableLiveData.value = ResourceState.Success(data)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    characterDetailMutableLiveData.value =
                        ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }

}