package com.example.notes.data.note

import com.example.notes.data.note.local.NotesLocalImpl
import com.example.notes.domain.NotesRepository
import com.example.notes.model.Note

class NotesDataImpl(
    private val notesLocalImpl: NotesLocalImpl
) : NotesRepository {
    override fun getNotes(): List<Note> {
        return notesLocalImpl.getNotes()
    }

    override fun getNote(noteId: Int): Note {
        return notesLocalImpl.getNote(noteId)
    }

    override fun addNote(note: Note) {
        notesLocalImpl.addNote(note)
    }

    override fun editNote(note: Note) {
        notesLocalImpl.editNote(note)
    }

    override fun deleteNote(noteId: Int) {
        notesLocalImpl.deleteNote(noteId)
    }
}