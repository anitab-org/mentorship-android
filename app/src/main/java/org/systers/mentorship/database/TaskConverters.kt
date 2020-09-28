package org.systers.mentorship.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.systers.mentorship.models.Task
import java.lang.reflect.Type

class TaskConverters {

    @TypeConverter
    fun stringToTask(json: String): List<Task> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Task>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun taskToString(tasks: List<Task>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Task>>() {}.type
        return gson.toJson(tasks, type)
    }
}