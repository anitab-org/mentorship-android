package org.systers.mentorship.repository

import org.systers.mentorship.db.AppDatabase
import org.systers.mentorship.models.Task
import org.systers.mentorship.models.User

class DbRepository(val db: AppDatabase) {

    /**
     * This will add the user's profile details to the database
     */
    suspend fun insertUser(user: User) = db.getDao().insertUser(user)

    /**
    * This will add the tasks to the database
    */
    suspend fun insertTask(task: Task) = db.getDao().insertTask(task)

    /**
     * This will delete the user's profile details from the database
     */
    suspend fun deleteUser(user: User) = db.getDao().deleteUser(user)

    /**
     * This will fetch the user's profile details from the database
     */
    fun getProfileDetails() = db.getDao().getProfile()

}