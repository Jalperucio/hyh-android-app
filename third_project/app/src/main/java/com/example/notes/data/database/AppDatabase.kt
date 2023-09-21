package com.example.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.data.note.local.dao.NotesDao
import com.example.notes.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}