package com.example.notes

import android.app.Application
import com.example.notes.di.baseModule
import com.example.notes.di.notesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NotesApplication)
            modules(baseModule, notesModule).allowOverride(true)
        }
    }

}