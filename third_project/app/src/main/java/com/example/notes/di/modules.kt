package com.example.notes.di

import com.example.notes.data.note.NotesDataImpl
import com.example.notes.data.note.local.NotesLocalImpl
import com.example.notes.domain.NotesRepository
import com.example.notes.domain.usecase.AddNoteUseCase
import com.example.notes.domain.usecase.DeleteNoteUseCase
import com.example.notes.domain.usecase.EditNoteUseCase
import com.example.notes.domain.usecase.GetNoteUseCase
import com.example.notes.domain.usecase.GetNotesUseCase
import com.example.notes.presentation.viewmodel.NotesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesModule = module {
    factory { NotesLocalImpl(androidContext()) }
    factory<NotesRepository> { NotesDataImpl(get()) }

    factory { AddNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }
    factory { EditNoteUseCase(get()) }
    factory { GetNotesUseCase(get()) }
    factory { GetNoteUseCase(get()) }

    viewModel { NotesViewModel(get(), get(), get(), get()) }
}