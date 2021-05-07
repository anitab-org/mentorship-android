package org.systers.mentorship.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import org.systers.mentorship.MentorshipApplication

/**
 * This class contains SharedPreferences utilities, such as methods to save and clear application sensitive data.
 */
class PreferenceManager {

    companion object {
        const val APPLICATION_PREFERENCE = "app-preferences"
        const val AUTH_TOKEN = "auth-token"
        const val REF_TOKEN = "refresh-token"
    }

    private val context: Context = MentorshipApplication.getContext()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            APPLICATION_PREFERENCE, Context.MODE_PRIVATE)

    /**
     * Saves the authorization token to SharedPreferences file.
     * @param authToken String which is the authorization token
     */
    @SuppressLint("ApplySharedPref")
    //Cannot use .apply(), it will take time to save the token. We need token ASAP
    fun putAuthToken(authToken: String) {
        sharedPreferences.edit().putString(AUTH_TOKEN, "Bearer $authToken").commit()
    }
    /**
     * Saves the refresh token to SharedPreferences file.
     * @param refToken String which is the refresh token
     */
    @SuppressLint("ApplySharedPref")
    //Cannot use .apply(), it will take time to save the token. We need token ASAP
    fun putRefreshToken(refToken: String) {
        sharedPreferences.edit().putString(REF_TOKEN, "Bearer $refToken").commit()
    }

    val authToken: String
        get() = sharedPreferences.getString(AUTH_TOKEN, "")

    val refToken: String
        get() = sharedPreferences.getString(REF_TOKEN, "")

    /**
     * Clears all the data that has been saved in the preferences file.
     */
    @SuppressLint("ApplySharedPref")
    fun clearAuthToken() {
        sharedPreferences.edit().remove(AUTH_TOKEN).commit()
    }

    @SuppressLint("ApplySharedPref")
    fun clearRefreshToken(){
       sharedPreferences.edit().remove(REF_TOKEN).commit()
    }

    fun clear(){
        sharedPreferences.edit().clear().apply()
    }
}
