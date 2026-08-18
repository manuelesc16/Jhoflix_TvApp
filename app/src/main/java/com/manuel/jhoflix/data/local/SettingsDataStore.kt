package com.manuel.jhoflix.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "jhoflix_settings")

/**
 * Guarda y expone la dirección del servidor Jhoflix usando DataStore.
 */
class SettingsDataStore(private val context: Context) {

    companion object {
        private val SERVER_URL_KEY = stringPreferencesKey("server_url")
        const val DEFAULT_URL = "http://192.168.1.100:5000"
    }

    val serverUrlFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[SERVER_URL_KEY] ?: DEFAULT_URL
    }

    suspend fun saveServerUrl(url: String) {
        context.dataStore.edit { prefs ->
            prefs[SERVER_URL_KEY] = url.trimEnd('/')
        }
    }
}
