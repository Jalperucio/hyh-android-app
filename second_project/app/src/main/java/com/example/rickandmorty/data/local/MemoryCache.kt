package com.example.rickandmorty.data.local

import com.example.rickandmorty.model.Character

class MemoryCache {

    var characterList: List<Character>? = null

    fun clearAll() {
        characterList = null
    }

}