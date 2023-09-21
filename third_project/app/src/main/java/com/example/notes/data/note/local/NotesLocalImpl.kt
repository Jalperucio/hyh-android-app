package com.example.notes.data.note.local

import com.example.notes.data.database.AppDatabase
import com.example.notes.model.Note

class NotesLocalImpl(
    private val appDatabase: AppDatabase
) {

    fun getNotes(): List<Note> {
        return appDatabase.notesDao().getNotes()
    }

    fun getNote(noteId: Int): Note {
        return appDatabase.notesDao().getNote(noteId)
    }

    fun addNote(note: Note) {
        appDatabase.notesDao().addNote(note)
    }

    fun editNote(note: Note) {
        appDatabase.notesDao().editNote(note)
    }

    fun deleteNote(note: Note) {
        appDatabase.notesDao().deleteNote(note)
    }

}