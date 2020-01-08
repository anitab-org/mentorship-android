package org.systers.mentorship.remote.datamanager

import androidx.lifecycle.LiveData
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.db.UsersDatabase
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.NetworkBoundResource
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.vo.Resource

/**
 * This class represents the data manager related to Users API
 */
class UserDataManager {

    private val apiManager = ApiManager.instance
    private val context = MentorshipApplication.getContext()
    private val userDao = UsersDatabase.getDatabase(context).userDao()

    /**
     * This will call the getVerifiedUsers method of UserService interface
     */
    suspend fun getUsers() = apiManager.userService.getVerifiedUsers()

    fun loadUser(login: String): LiveData<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, List<User>>() {
            override fun saveCallResult(item: List<User>) {
                for (user in item) {
                    userDao.insert(user)
                }
            }

            override fun shouldFetch(data: List<User>?) = data == null

            override fun loadFromDb() = userDao.findAll()

            override fun createCall() = apiManager.userService.getVerifiedUsersLD()
        }.asLiveData()
    }

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
