package org.systers.mentorship.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.models.User

/**
 * This class contains SharedPreferences utilities, such as methods to save and clear application sensitive data.
 */
class PreferenceManager {

    companion object {
        const val APPLICATION_PREFERENCE = "app-preferences"
        const val AUTH_TOKEN = "auth-token"
        const val PROFILE_DETAIL = "profile_detail"
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

    fun <User> putProfileDetails(`user`: User){
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`user`)
        //Save that String in SharedPreferences
        sharedPreferences.edit().putString(PROFILE_DETAIL, jsonString).apply()
    }

    fun getProfileDetails(): User? {
        //We read JSON String which was saved.
        val value = sharedPreferences.getString(PROFILE_DETAIL, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object.
        return GsonBuilder().create().fromJson(value, User::class.java)
    }



    /**
     * Clears all the data that has been saved in the preferences file.
     */
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
