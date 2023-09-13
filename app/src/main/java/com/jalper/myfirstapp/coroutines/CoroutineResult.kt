package com.jalper.myfirstapp.coroutines

sealed class CoroutineResult<T> {
    class Loading<T> : CoroutineResult<T>()
    data class Success<T>(val result: T) : CoroutineResult<T>()

    data class Error<T>(val error: String) : CoroutineResult<T>()
}
