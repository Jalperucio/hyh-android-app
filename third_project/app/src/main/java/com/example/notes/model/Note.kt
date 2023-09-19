package com.example.notes.model

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val date: Long,
    val isFavorite: Boolean
)
