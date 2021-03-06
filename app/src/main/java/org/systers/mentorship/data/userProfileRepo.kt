package org.systers.mentorship.data

import androidx.lifecycle.LiveData
import org.systers.mentorship.models.User

class userProfileRepo(private val userProfileDao: userProfileDao) {
    val getUserProfile: LiveData<User> = userProfileDao.getUserProfile()
    suspend fun storeUserProfile(user: User){
        userProfileDao.storeUserProfile(user)
    }
    suspend fun deleteUserProfile(){
        userProfileDao.deleteAllUserData()
    }
}