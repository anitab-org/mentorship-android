package org.systers.mentorship.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import org.systers.mentorship.MentorshipApplication

/**
 * Created by murad on 7/26/18.
 */
class PreferenceManager {

    companion object {
        const val APPLICATION_PREFERENCE = "app-preferences"
        const val AUTH_TOKEN = "auth-token"
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

    val authToken: String
        get() = sharedPreferences.getString(AUTH_TOKEN, "")
}
