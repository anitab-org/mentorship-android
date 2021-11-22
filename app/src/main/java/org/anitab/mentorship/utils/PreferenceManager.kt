package org.anitab.mentorship.utils

import android.content.Context
import androidx.datastore.preferences.clear
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.anitab.mentorship.MentorshipApplication

/**
 * This class contains DataStore utilities, such as methods to save and clear application sensitive data.
 */
class PreferenceManager {

    companion object {
        const val APPLICATION_PREFERENCE = "app-preferences"
        const val AUTH_TOKEN = "auth-token"
    }

    private val context: Context = MentorshipApplication.getContext()

    private val dataStorePreferences = context.createDataStore(APPLICATION_PREFERENCE)
    private val authTokenKey = preferencesKey<String>(AUTH_TOKEN)
    /**
     * Saves the authorization token to DataStorePreferences file.
     * @param authToken String which is the authorization token
     */
    suspend fun putAuthToken(authToken: String) {
        dataStorePreferences.edit { preferences ->
            preferences[authTokenKey] = "Bearer $authToken"
        }
    }

    val authToken = runBlocking {
        dataStorePreferences.data
            .map { preferences ->
                preferences[authTokenKey] ?: ""
            }.first()
    }

    /**
     * Clears all the data that has been saved in the preferences file.
     */
    fun clear() = runBlocking {
        dataStorePreferences.edit { preferences ->
            preferences.clear()
        }
    }
}
