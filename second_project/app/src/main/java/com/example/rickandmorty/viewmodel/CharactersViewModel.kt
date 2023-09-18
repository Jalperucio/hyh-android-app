package com.example.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.ApiClient
import com.example.rickandmorty.api.RickAndMortyService
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias CharacterListState = ResourceState<List<Character>>
typealias CharacterDetailState = ResourceState<Character>

class CharactersViewModel : ViewModel() {

    private val rickAndMortyService = ApiClient.retrofit.create(RickAndMortyService::class.java)

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
                val response = rickAndMortyService.getCharacters()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        characterMutableLiveData.value = ResourceState.Success(response.body()!!.characters)
                    } else {
                        characterMutableLiveData.value = ResourceState.Error(response.errorBody()?.string().orEmpty())
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    characterMutableLiveData.value = ResourceState.Error("Ha ocurrido un error")
                }
            }
        }
    }

    fun fetchCharacter(characterId: Int) {
        characterDetailMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = rickAndMortyService.getCharacter(characterId)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        characterDetailMutableLiveData.value = ResourceState.Success(response.body()!!)
                    } else {
                        characterDetailMutableLiveData.value = ResourceState.Error(response.errorBody()?.string().orEmpty())
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    characterDetailMutableLiveData.value = ResourceState.Error("Ha ocurrido un error")
                }
            }
        }
    }

}