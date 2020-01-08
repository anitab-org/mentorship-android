package org.systers.mentorship.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.systers.mentorship.models.Task

object TaskConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun listOfTasksToString(tasks: List<Task>): String? {
        return gson.toJson(tasks)
    }

    @TypeConverter
    @JvmStatic
    fun stringToListOfTasks(encodedTasks: String?): List<Task>? {
        val typeToken = object : TypeToken<ArrayList<Task>>() {}.type

        return gson.fromJson(encodedTasks, typeToken)
    }
}
