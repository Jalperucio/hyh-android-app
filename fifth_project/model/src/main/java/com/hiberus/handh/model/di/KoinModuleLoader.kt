package com.hiberus.handh.model.di

import android.content.Context
import org.koin.core.module.Module

interface KoinModuleLoader {
    fun getKoinModules(context: Context): List<Module>
}