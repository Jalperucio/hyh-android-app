package com.example.notes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.usecase.AddNoteUseCase
import com.example.notes.domain.usecase.DeleteNoteUseCase
import com.example.notes.domain.usecase.EditNoteUseCase
import com.example.notes.domain.usecase.GetNoteUseCase
import com.example.notes.domain.usecase.GetNotesUseCase
import com.example.notes.model.Note
import com.example.notes.model.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias NoteListState = ResourceState<List<Note>>
typealias AddNoteState = ResourceState<Void?>
typealias NoteDetailState = ResourceState<Note>
typealias EditNoteState = ResourceState<Void?>
typealias DeleteNoteState = ResourceState<Void?>

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _noteListLiveData = MutableLiveData<NoteListState>()
    val noteListLiveData: LiveData<NoteListState> get() = _noteListLiveData

    private val _addNoteLiveData = MutableLiveData<AddNoteState>()
    val addNoteLiveData: LiveData<AddNoteState> get() = _addNoteLiveData

    private val _noteDetailLiveData = MutableLiveData<NoteDetailState>()
    val noteDetailLiveData: LiveData<NoteDetailState> get() = _noteDetailLiveData

    private val _editNoteLiveData = MutableLiveData<EditNoteState>()
    val editNoteLiveData: LiveData<EditNoteState> get() = _editNoteLiveData

    private val _deleteNoteLiveData = MutableLiveData<DeleteNoteState>()
    val deleteNoteLiveData: LiveData<DeleteNoteState> get() = _deleteNoteLiveData

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
                withContext(Dispatchers.Main) {
                    _noteListLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _noteListLiveData.value = ResourceState.None()
                }
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
                withContext(Dispatchers.Main) {
                    _addNoteLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _addNoteLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun fetchNote(noteId: Int) {
        _noteDetailLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val note = getNoteUseCase.execute(noteId)

                withContext(Dispatchers.Main) {
                    _noteDetailLiveData.value = ResourceState.Success(note)
                    _noteDetailLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _noteDetailLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _noteDetailLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun editNote(note: Note) {
        _editNoteLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                editNoteUseCase.execute(note)

                withContext(Dispatchers.Main) {
                    _editNoteLiveData.value = ResourceState.Success(null)
                    _editNoteLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _editNoteLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _editNoteLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        _deleteNoteLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteNoteUseCase.execute(note)

                withContext(Dispatchers.Main) {
                    _deleteNoteLiveData.value = ResourceState.Success(null)
                    _deleteNoteLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _deleteNoteLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _deleteNoteLiveData.value = ResourceState.None()
                }
            }
        }
    }

}