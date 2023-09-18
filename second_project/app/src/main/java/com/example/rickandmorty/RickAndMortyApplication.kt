package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.baseModule
import com.example.rickandmorty.di.charactersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RickAndMortyApplication)
            modules(listOf(baseModule, charactersModule)).allowOverride(true)
        }
    }

}