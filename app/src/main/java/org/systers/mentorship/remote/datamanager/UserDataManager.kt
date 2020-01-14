package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.Custom
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Users API
 */
class UserDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call the getVerifiedUsers method of UserService interface
     * @return an Custom of a list of [User]
     */
    fun getUsers(): Custom<List<User>> {
        return apiManager.userService.getVerifiedUsers()
    }

    /**
     * This will call the getUser method of UserService interface
     * @return an Custom of [User]
     */
    fun getUser(userId: Int): Custom<User> {
        return apiManager.userService.getUser(userId)
    }

    /**
     * This will call the getUser method of UserService interface
     * @return an Custom of [User]
     */
    fun getUser(): Custom<User> {
        return apiManager.userService.getUser()
    }

    /**
     * This will call the updateUser method of UserService interface
     * @return an Custom of [CustomResponse]
     */
    fun updateUser(user: User): Custom<CustomResponse> {
        return apiManager.userService.updateUser(user)
    }

    /**
     * This will call the updatePassword method of UserService interface
     * @return an Custom of [CustomResponse]
     */
    fun updatePassword(changePassword: ChangePassword): Custom<CustomResponse> {
        return apiManager.userService.updatePassword(changePassword)
    }

    /**
     * This function fetches user statistics
     * @return an Custom of [HomeStatistics]
     */
    fun getHomeStats(): Custom<HomeStatistics> {
        return apiManager.userService.getHomeStats()
    }
}
