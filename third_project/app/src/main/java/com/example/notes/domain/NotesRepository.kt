package com.example.notes.domain

import com.example.notes.model.Note

interface NotesRepository {

    fun getNotes(): List<Note>

    fun getNote(noteId: Int): Note

    fun addNote(note: Note)

    fun editNote(note: Note)

    fun deleteNote(noteId: Int)

}