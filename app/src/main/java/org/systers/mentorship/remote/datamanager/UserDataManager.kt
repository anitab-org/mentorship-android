package org.systers.mentorship.remote.datamanager

import androidx.lifecycle.LiveData
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.db.AppDatabase
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.NetworkBoundResource
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.vo.Resource
import org.systers.mentorship.vo.UserVO

/**
 * This class represents the data manager related to Users API
 */
class UserDataManager {
    private val TAG = this::class.java.simpleName

    private val apiManager = ApiManager.instance
    private val context = MentorshipApplication.getContext()
    private val userDao = AppDatabase.getInstance(context).userDao()

    /**
     * Provides list of of verified users using the best (most optimal )
     */
    fun getVerifiedUsers(): LiveData<Resource<List<UserVO>>> {
        return object : NetworkBoundResource<List<UserVO>, List<UserVO>>() {
            override fun saveCallResult(item: List<UserVO>) = userDao.insertUsers(item)

            override fun shouldFetch(data: List<UserVO>?) = data == null || data.isEmpty()

            override fun loadFromDb() = userDao.findAll()

            override fun createCall() = apiManager.userService.getVerifiedUsers()
        }.asLiveData()
    }

    /**
     * This will call the getUser method of UserService interface
     */
    fun getUser(userId: Int): LiveData<Resource<UserVO>> {
        return object : NetworkBoundResource<UserVO, UserVO>() {
            override fun saveCallResult(item: UserVO) = userDao.insertUser(item)

            override fun shouldFetch(data: UserVO?) = data == null

            override fun loadFromDb() = userDao.findById(userId)

            override fun createCall() = apiManager.userService.getUser(userId)

        }.asLiveData()
    }

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
    suspend fun updatePassword(changePassword: ChangePassword) =
        apiManager.userService.updatePassword(
            changePassword
        )

    /**
     * This function fetches user statistics
     */
    fun getHomeStats(): LiveData<Resource<HomeStatistics>> {
        return object : NetworkBoundResource<HomeStatistics, HomeStatistics>() {
            override fun saveCallResult(item: HomeStatistics) = userDao.insertHomeStats(item)

            override fun shouldFetch(data: HomeStatistics?): Boolean = data == null

            override fun loadFromDb() = userDao.getHomeStats()

            override fun createCall() = apiManager.userService.getHomeStats()
        }.asLiveData()
    }
}
