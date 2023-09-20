package com.example.notes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.usecase.AddNoteUseCase
import com.example.notes.domain.usecase.GetNotesUseCase
import com.example.notes.model.Note
import com.example.notes.model.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias NoteListState = ResourceState<List<Note>>
typealias AddNoteState = ResourceState<Void?>

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val _noteListLiveData = MutableLiveData<NoteListState>()
    val noteListLiveData: LiveData<NoteListState> get() = _noteListLiveData

    private val _addNoteLiveData = MutableLiveData<AddNoteState>()
    val addNoteLiveData: LiveData<AddNoteState> get() = _addNoteLiveData

    fun fetchNoteList() {
        _noteListLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val notes = getNotesUseCase.execute()

                withContext(Dispatchers.Main) {
                    _noteListLiveData.value = ResourceState.Success(notes)
                    _noteListLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                _noteListLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                _noteListLiveData.value = ResourceState.None()
            }
        }
    }

    fun addNote(note: Note) {
        _addNoteLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                addNoteUseCase.execute(note)

                withContext(Dispatchers.Main) {
                    _addNoteLiveData.value = ResourceState.Success(null)
                    _addNoteLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                _addNoteLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                _addNoteLiveData.value = ResourceState.None()
            }
        }
    }

}