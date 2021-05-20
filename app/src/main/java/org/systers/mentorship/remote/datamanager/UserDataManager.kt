package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.remote.requests.PaginationRequest
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Users API
 */
class UserDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call the getVerifiedUsers method of UserService interface
     * @return an Observable of a list of [User]
     */
    fun getUsers(): Observable<List<User>> {
        return apiManager.userService.getVerifiedUsers()
    }

    /**
     * This will call the getVerifiedUsers(pagination) method of UserService interface
     * @return an Observable of a list of [User]
     */
    fun getUsers(paginationRequest: PaginationRequest): Observable<List<User>> {
        return apiManager.userService.getVerifiedUsers(paginationRequest.pagination)
    }

    /**
     * This will call the getUser method of UserService interface
     * @return an Observable of [User]
     */
    fun getUser(userId: Int): Observable<User> {
        return apiManager.userService.getUser(userId)
    }

    /**
     * This will call the getUser method of UserService interface
     * @return an Observable of [User]
     */
    fun getUser(): Observable<User> {
        return apiManager.userService.getUser()
    }

    /**
     * This will call the updateUser method of UserService interface
     * @return an Observable of [CustomResponse]
     */
    fun updateUser(user: User): Observable<CustomResponse> {
        return apiManager.userService.updateUser(user)
    }

    /**
     * This will call the updatePassword method of UserService interface
     * @return an Observable of [CustomResponse]
     */
    fun updatePassword(changePassword: ChangePassword): Observable<CustomResponse> {
        return apiManager.userService.updatePassword(changePassword)
    }

    /**
     * This function fetches user statistics
     * @return an observable of [HomeStatistics]
     */
    fun getHomeStats(): Observable<HomeStatistics> {
        return apiManager.userService.getHomeStats()
    }
}
