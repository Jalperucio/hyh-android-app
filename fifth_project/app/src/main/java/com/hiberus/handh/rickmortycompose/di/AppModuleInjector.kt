package com.hiberus.handh.rickmortycompose.di

import android.content.Context
import com.hiberus.handh.domain.di.DomainModuleInjection
import com.hiberus.handh.model.di.KoinModuleLoader
import com.hiberus.handh.rickmortycompose.feature.characters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModuleInjector: KoinModuleLoader {
    override fun getKoinModules(context: Context): List<Module> =
        DomainModuleInjection.getKoinModules(context)
            .union(
                listOf(
                    module {
                        viewModel { CharactersViewModel(get()) }
                    }
                )
            ).toList()
}