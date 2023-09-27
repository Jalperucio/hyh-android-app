package com.hiberus.handh.data.feature.settings

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

internal class SettingsRepositoryImpl(private val context: Context): SettingsRepository {

    private val Context.dataStore by preferencesDataStore("RickAndMortyPreferences")
}