package org.anitab.mentorship.remote.datamanager

import androidx.lifecycle.map
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.anitab.mentorship.local.UserDatabase
import org.anitab.mentorship.models.HomeStatistics
import org.anitab.mentorship.models.User
import org.anitab.mentorship.remote.ApiManager
import org.anitab.mentorship.remote.requests.ChangePassword
import org.anitab.mentorship.remote.responses.CustomResponse
import org.anitab.mentorship.utils.Constants.ITEMS_PER_PAGE

/**
 * This class represents the data manager related to Users API
 */
class UserDataManager(
    private val apiManager: ApiManager,
    private val userDatabase: UserDatabase,
    private val dispatcher: CoroutineDispatcher
) {

    /** Paging 3 user data management starts **/

    /**
     * This will call the getAllUsers() method from UserDao interface
     * @return stream of [User] list as PagingData with the help of [UserRemoteMediator]
     * which manages making network call and storing user list in room db.
     */

    @OptIn(ExperimentalPagingApi::class)
    fun getAllUsers(): LiveData<PagingData<User>> {
        val pagingSourceFactory = { userDatabase.userDao().getAllUsers() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(apiManager.userService, userDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }

    /**
     * This will call the getUsersWhoAreAvailableToMentor() method from UserDao interface
     * @return stream of [User] list, who are available to be mentored as PagingData with
     * the help of [UserRemoteMediator] which manages making network call and storing user
     * list in room db.
     */
    @OptIn(ExperimentalPagingApi::class)
    fun getUsersWhoAreAvailableToMentor(): LiveData<PagingData<User>> {
        val pagingSourceFactory = { userDatabase.userDao().getAllUsers() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(apiManager.userService, userDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
            .map { pagingData -> pagingData.filter { user -> user.availableToMentor != null && user.availableToMentor == true } }
    }

    /**
     * This will call the getUsersWhoAreNeedMentoring() method from UserDao interface
     * @return stream of [User] list, who are need mentoring as PagingData with the help of
     * [UserRemoteMediator] which manages making network call and storing user list in room db.
     */
    @OptIn(ExperimentalPagingApi::class)
    fun getUsersWhoAreNeedMentoring(): LiveData<PagingData<User>> {
        val pagingSourceFactory = { userDatabase.userDao().getAllUsers() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(apiManager.userService, userDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
            .map { pagingData -> pagingData.filter { user -> user.needMentoring != null && user.needMentoring == true } }
    }

    /**
     * This will call the getUserWhoHaveSkills() method from UserDao interface
     * @return stream of [User] list, who have at least 1 skill as PagingData with the help of
     * [UserRemoteMediator] which manages making network call and storing user list in room db.
     */
    @OptIn(ExperimentalPagingApi::class)
    fun getUserWhoHaveSkills(): LiveData<PagingData<User>> {
        val pagingSourceFactory = { userDatabase.userDao().getAllUsers() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(apiManager.userService, userDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
            .map { pagingData -> pagingData.filter { user -> user.skills != null } }
    }

    /** Paging 3 user data management end **/

    /**
     * This will call the getUser method of UserService interface
     * @return an Observable of [User]
     */
    suspend fun getUser(userId: Int): User {
        return withContext(dispatcher) { apiManager.userService.getUser(userId) }
    }

    /**
     * This will call the getUser method of UserService interface
     * @return an Observable of [User]
     */
    suspend fun getUser(): User {
        return withContext(dispatcher) { apiManager.userService.getUser() }
    }

    /**
     * This will call the updateUser method of UserService interface
     * @return an Observable of [CustomResponse]
     */
    suspend fun updateUser(user: User): CustomResponse {
        return withContext(dispatcher) { apiManager.userService.updateUser(user) }
    }

    /**
     * This will call the updatePassword method of UserService interface
     * @return an Observable of [CustomResponse]
     */
    suspend fun updatePassword(changePassword: ChangePassword): CustomResponse {
        return withContext(dispatcher) { apiManager.userService.updatePassword(changePassword) }
    }

    /**
     * This function fetches user statistics
     * @return an observable of [HomeStatistics]
     */
    suspend fun getHomeStats(): HomeStatistics {
        return withContext(dispatcher) { apiManager.userService.getHomeStats() }
    }
}
