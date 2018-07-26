package org.systers.mentorship.utils

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

    var authToken: String
        set(authToken) = sharedPreferences.edit().putString(AUTH_TOKEN, "Bearer $authToken").apply()
        get() = sharedPreferences.getString(AUTH_TOKEN, "")
}
