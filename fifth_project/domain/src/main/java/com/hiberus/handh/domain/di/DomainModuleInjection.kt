package com.hiberus.handh.domain.di

import android.content.Context
import com.hiberus.handh.data.di.DataModuleInjector
import com.hiberus.handh.domain.feature.characters.AllCharactersUseCase
import com.hiberus.handh.model.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModuleInjection: KoinModuleLoader {
    override fun getKoinModules(context: Context): List<Module> =
        DataModuleInjector.getKoinModules(context)
            .union(
                listOf(
                    module {
                        factory { AllCharactersUseCase(get()) }
                    }
                )
            ).toList()
}