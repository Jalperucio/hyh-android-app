package com.example.notes.domain.usecase

import com.example.notes.domain.NotesRepository
import com.example.notes.model.Note

class GetNoteUseCase(
    private val notesRepository: NotesRepository
) {

    fun execute(noteId: Int): Note {
        return notesRepository.getNote(noteId)
    }

}