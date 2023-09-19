package com.example.notes.domain.usecase

import com.example.notes.domain.NotesRepository
import com.example.notes.model.Note

class GetNotesUseCase(
    private val notesRepository: NotesRepository
) {

    fun execute(): List<Note> {
        return notesRepository.getNotes()
    }

}