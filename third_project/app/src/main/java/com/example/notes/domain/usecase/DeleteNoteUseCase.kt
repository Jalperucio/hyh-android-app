package com.example.notes.domain.usecase

import com.example.notes.domain.NotesRepository

class DeleteNoteUseCase(
    private val notesRepository: NotesRepository
) {

    fun execute(noteId: Int) {
        notesRepository.deleteNote(noteId)
    }

}