package org.anitab.mentorship.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.anitab.mentorship.MentorshipApplication
import org.anitab.mentorship.utils.PreferenceManager.Companion.APPLICATION_PREFERENCE

/**
 * This class contains SharedPreferences utilities, such as methods to save and clear application sensitive data.
 */
private val Context.datastore by preferencesDataStore(APPLICATION_PREFERENCE)
class PreferenceManager {

    companion object {
        const val APPLICATION_PREFERENCE = "app-preferences"
        const val AUTH_TOKEN = "auth-token"
    }
    private val authTokenKey = stringPreferencesKey(AUTH_TOKEN)
    private val context: Context = MentorshipApplication.getContext()

    /**
     * Saves the authorization token to DataStore file.
     * @param authToken String which is the authorization token
     */
    @SuppressLint("ApplySharedPref")
    suspend fun putAuthToken(authToken: String) {
        context.datastore.edit { preferences ->
            preferences[authTokenKey] = "Bearer $authToken"
        }
    }

    val authToken = runBlocking {
        context.datastore.data.map { preferences ->
            preferences[authTokenKey]?:""
        }.first()
    }
    /**
     * Clears all the data that has been saved in the preferences file.
     */
    fun clear() = runBlocking {
        context.datastore.edit { preferences ->
            preferences.clear()
        }
    }
}
