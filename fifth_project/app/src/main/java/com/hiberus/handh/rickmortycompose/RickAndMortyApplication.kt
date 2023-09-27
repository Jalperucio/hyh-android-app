package com.hiberus.handh.rickmortycompose

import android.app.Application
import com.hiberus.handh.rickmortycompose.di.AppModuleInjector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class RickAndMortyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@RickAndMortyApplication)
            modules(
                AppModuleInjector.getKoinModules(
                    this@RickAndMortyApplication
                )
            )
        }
    }
}