package org.systers.mentorship.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.models.Relationship

/**
 * This class contains SharedPreferences utilities, such as methods to save and clear application sensitive data
 * and notification data.
 */
class PreferenceManager {

    companion object {
        const val APPLICATION_PREFERENCE = "app-preferences"
        const val AUTH_TOKEN = "auth-token"
        const val NOTIFICATIONS = "notifications"
        const val RELATIONS = "relations"
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

    /**
     * Saves the notification list to SharedPreferences file by converting to JSON
     * @param notificationList which contains list
     */
    fun putNotifications(notificationList: List<Relationship>){
        //convert to JSON string and save
        val gson = Gson()
        val json = gson.toJson(notificationList)
        sharedPreferences.edit().putString(NOTIFICATIONS, json).commit()
    }

    fun getNotificationList(): MutableList<Relationship>
    {
        val notificationList = mutableListOf<Relationship>()
        val json = sharedPreferences.getString(NOTIFICATIONS, null)
        if (json != null){
            val gson = Gson()
            val type = object : TypeToken<List<Relationship>>() {
            }.getType()
            notificationList.addAll(gson.fromJson<MutableList<Relationship>>(json, type))
        }
        return notificationList
    }

    /**
     * Saves the requestsList list to SharedPreferences which is needed to find notifications
     * @param requestsList which contains list
     */
    fun putRequests(requestsList: List<Relationship>){
        //convert to JSON string and save
        val gson = Gson()
        val json = gson.toJson(requestsList)
        sharedPreferences.edit().putString(RELATIONS, json).commit()
    }

    fun getRequestsList(): MutableList<Relationship>
    {
        val requestsList = mutableListOf<Relationship>()
        val json = sharedPreferences.getString(RELATIONS, null)
        if (json != null){
            val gson = Gson()
            val type = object : TypeToken<List<Relationship>>() {

            }.getType()
            requestsList.addAll(gson.fromJson<MutableList<Relationship>>(json, type))
        }
        return requestsList
    }

    /**
     * Clears the notifications data that has been saved in the preferences file.
     */
    fun clearNotifications() {
        sharedPreferences.edit().remove(NOTIFICATIONS).apply()
    }

    /**
     * Clears all the data that has been saved in the preferences file.
     */
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
