package com.example.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey var id: Int = -1,
    val title: String,
    val description: String,
    val date: Long,
    var isFavorite: Boolean = false
)
