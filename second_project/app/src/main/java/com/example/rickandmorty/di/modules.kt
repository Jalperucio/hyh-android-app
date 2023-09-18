package com.example.rickandmorty.di

import com.example.rickandmorty.data.character.CharactersDataImpl
import com.example.rickandmorty.data.character.local.CharactersLocalImpl
import com.example.rickandmorty.data.character.remote.CharactersRemoteImpl
import com.example.rickandmorty.data.local.MemoryCache
import com.example.rickandmorty.data.remote.ApiClient
import com.example.rickandmorty.data.remote.RickAndMortyService
import com.example.rickandmorty.domain.CharactersRepository
import com.example.rickandmorty.domain.usecase.GetCharacterDetailUseCase
import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseModule = module {
    single { MemoryCache() }
    single<RickAndMortyService> { ApiClient.retrofit.create(RickAndMortyService::class.java) }
}

val charactersModule = module {
    factory { CharactersLocalImpl(get()) }
    factory { CharactersRemoteImpl(get()) }
    factory<CharactersRepository> { CharactersDataImpl(get(), get()) }

    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterDetailUseCase(get()) }

    viewModel { CharactersViewModel(get(), get()) }
}