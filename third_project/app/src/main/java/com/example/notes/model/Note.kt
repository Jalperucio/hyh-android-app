package com.example.notes.model

data class Note(
    var id: Int = -1,
    val title: String,
    val description: String,
    val date: Long,
    var isFavorite: Boolean = false
)
