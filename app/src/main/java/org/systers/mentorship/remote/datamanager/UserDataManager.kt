package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.models.User
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.ChangePassword

/**
 * This class represents the data manager related to Users API
 */
class UserDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call the getVerifiedUsers method of UserService interface
     */
    suspend fun getUsers() = apiManager.userService.getVerifiedUsers()

    /**
     * This will call the getUser method of UserService interface
     */
    suspend fun getUser(userId: Int) = apiManager.userService.getUser(userId)

    /**
     * This will call the getUser method of UserService interface
     */
    suspend fun getUser() = apiManager.userService.getUser()

    /**
     * This will call the updateUser method of UserService interface
     */
    suspend fun updateUser(user: User) = apiManager.userService.updateUser(user)

    /**
     * This will call the updatePassword method of UserService interface
     */
    suspend fun updatePassword(changePassword: ChangePassword) = apiManager.userService.updatePassword(
            changePassword)

    /**
     * This function fetches user statistics
     */
    suspend fun getHomeStats() = apiManager.userService.getHomeStats()
}
