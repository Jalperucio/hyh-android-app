package com.example.notes.domain.usecase

import com.example.notes.domain.NotesRepository
import com.example.notes.model.Note

class DeleteNoteUseCase(
    private val notesRepository: NotesRepository
) {

    fun execute(note: Note) {
        notesRepository.deleteNote(note)
    }

}