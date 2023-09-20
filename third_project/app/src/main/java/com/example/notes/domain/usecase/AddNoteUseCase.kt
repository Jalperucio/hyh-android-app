package com.example.notes.domain.usecase

import com.example.notes.domain.NotesRepository
import com.example.notes.model.Note

class AddNoteUseCase(
    private val notesRepository: NotesRepository
) {

    fun execute(note: Note) {
        note.id = (System.currentTimeMillis() / 1000).toInt()
        notesRepository.addNote(note)
    }

}