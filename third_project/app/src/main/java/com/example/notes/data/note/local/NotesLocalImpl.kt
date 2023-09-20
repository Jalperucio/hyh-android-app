package com.example.notes.data.note.local

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.notes.model.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NotesLocalImpl(
    private val context: Context
) {

    companion object {
        const val NOTE_LIST_KEY = "NOTE_LIST_KEY"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getNotes(): List<Note> {
        val json = prefs.getString(NOTE_LIST_KEY, "[]") ?: "[]"
        val token = object : TypeToken<List<Note>>(){}.type

        return Gson().fromJson(json, token)
    }

    fun getNote(noteId: Int): Note {
        val notes = getNotes()

        return notes.first { note ->
            note.id == noteId
        }
    }

    fun addNote(note: Note) {
        val notes = getNotes().toMutableList()
        notes.add(note)

        saveInPreferences(notes)
    }

    fun editNote(note: Note) {
        val notes = getNotes().toMutableList()
        val selectedNote = getNote(note.id)

        notes[notes.indexOf(selectedNote)] = note

        saveInPreferences(notes)
    }

    fun deleteNote(noteId: Int) {
        val notes = getNotes().toMutableList()
        val selectedNote = getNote(noteId)

        notes.remove(selectedNote)

        saveInPreferences(notes)
    }

    private fun saveInPreferences(list: List<Note>) {
        val json = Gson().toJson(list)

        prefs.edit()
            .putString(NOTE_LIST_KEY, json)
            .apply()
    }

}