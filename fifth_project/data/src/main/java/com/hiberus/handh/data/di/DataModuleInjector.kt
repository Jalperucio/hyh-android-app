package com.hiberus.handh.data.di

import android.content.Context
import com.hiberus.handh.data.feature.characters.datastore.cache.datastore.CacheCharacterDataStoreImpl
import com.hiberus.handh.data.feature.characters.datastore.interfaces.CharactersDataStore
import com.hiberus.handh.data.feature.characters.datastore.remote.CharactersService
import com.hiberus.handh.data.feature.characters.datastore.remote.datastore.RemoteCharacterDataStoreImpl
import com.hiberus.handh.data.feature.characters.factory.RickAndMortyCharacterFactory
import com.hiberus.handh.data.feature.characters.paging.CharactersPaging
import com.hiberus.handh.data.feature.characters.repository.CharactersRepository
import com.hiberus.handh.data.feature.characters.repository.CharactersRepositoryImpl
import com.hiberus.handh.data.feature.episodes.datastore.remote.EpisodeService
import com.hiberus.handh.data.feature.locations.datastore.remote.LocationsService
import com.hiberus.handh.data.feature.services.remote.RickAndMortyService
import com.hiberus.handh.model.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModuleInjector: KoinModuleLoader {
    override fun getKoinModules(context: Context): List<Module> = listOf(
        module {
            single <CharactersService>(named("characterService")){
                Retrofit.Builder()
                    .baseUrl(RickAndMortyService.BASE_API)
                    .addConverterFactory(GsonConverterFactory.create(RickAndMortyService.makeGson()))
                    .client(RickAndMortyService.makeOkHttpClient())
                    .build().create(CharactersService::class.java)
            }
            single <LocationsService>{
                Retrofit.Builder()
                    .baseUrl(RickAndMortyService.BASE_API)
                    .addConverterFactory(GsonConverterFactory.create(RickAndMortyService.makeGson()))
                    .client(RickAndMortyService.makeOkHttpClient())
                    .build().create(LocationsService::class.java)
            }
            single <EpisodeService>{
                Retrofit.Builder()
                    .baseUrl(RickAndMortyService.BASE_API)
                    .addConverterFactory(GsonConverterFactory.create(RickAndMortyService.makeGson()))
                    .client(RickAndMortyService.makeOkHttpClient())
                    .build().create(EpisodeService::class.java)
            }

            factory<CharactersDataStore>(named("cache")) {
                CacheCharacterDataStoreImpl()
            }
            factory<CharactersDataStore>(named("remote")) {
                RemoteCharacterDataStoreImpl(get(named("characterService")))
            }

            factory { RickAndMortyCharacterFactory(get(named("cache")), get(named("remote"))) }

            factory<CharactersRepository>{ CharactersRepositoryImpl(get()) }

            factory { CharactersPaging(get()) }
        }
    )
}