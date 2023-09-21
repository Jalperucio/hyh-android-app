package com.example.notes.data.note.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :noteId LIMIT 1")
    fun getNote(noteId: Int): Note

    @Update
    fun editNote(note: Note)

    @Insert
    fun addNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

}