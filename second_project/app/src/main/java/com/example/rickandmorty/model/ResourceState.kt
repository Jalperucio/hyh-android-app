package com.example.rickandmorty.model

sealed class ResourceState<T> {
    class Loading<T> : ResourceState<T>()
    data class Success<T>(val result: T) : ResourceState<T>()
    data class Error<T>(val error: String) : ResourceState<T>()
}
