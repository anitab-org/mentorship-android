package org.systers.mentorship.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.remote.responses.AuthRefreshToken
import org.systers.mentorship.remote.responses.AuthToken

/**
 * This class contains SharedPreferences utilities, such as methods to save and clear application sensitive data.
 */
class PreferenceManager {

    companion object {
        const val APPLICATION_PREFERENCE = "app-preferences"
        const val AUTH_TOKEN = "auth-token"
        const val AUTH_EXPIRY = "auth-expiry"
        const val REFRESH_TOKEN = "refresh-token"
        const val REFRESH_EXPIRY = "refresh-expiry"
    }

    private val context: Context = MentorshipApplication.getContext()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            APPLICATION_PREFERENCE, Context.MODE_PRIVATE)

    /**
     * Saves the authorization token to SharedPreferences file.
     * @param authRefreshToken AuthRefreshToken which is the authorization and refresh token
     */
    @SuppressLint("ApplySharedPref")
    //Cannot use .apply(), it will take time to save the token. We need token ASAP
    fun putAuthRefreshToken(authRefreshToken: AuthRefreshToken) {
        sharedPreferences.edit().putString(AUTH_TOKEN, "Bearer ${authRefreshToken.authToken}").commit()
        sharedPreferences.edit().apply {
            putFloat(AUTH_EXPIRY, authRefreshToken.accessExpiry)
            putString(REFRESH_TOKEN, "Bearer ${authRefreshToken.refreshToken}")
            putFloat(REFRESH_EXPIRY, authRefreshToken.refreshExpiry)
            apply()
        }
    }

    /**
     * Saves the authorization token to SharedPreferences file.
     * @param authToken AuthToken which is the authorization token
     */
    @SuppressLint("ApplySharedPref")
    fun putAuthToken(authToken: AuthToken) {
        sharedPreferences.edit().apply {
            putString(AUTH_TOKEN, "Bearer ${authToken.authToken}")
            putFloat(AUTH_EXPIRY, authToken.accessExpiry)
            apply()
        }
    }

    val authToken: String
        get() = sharedPreferences.getString(AUTH_TOKEN, "") ?: ""

    val authExpiry: Float
        get() = sharedPreferences.getFloat(AUTH_EXPIRY, 0f)

    val refreshToken: String
        get() = sharedPreferences.getString(REFRESH_TOKEN, "") ?: ""

    val refreshExpiry: Float
        get() = sharedPreferences.getFloat(REFRESH_EXPIRY, 0f)

    /**
     * Clears all the data that has been saved in the preferences file.
     */
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
