package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.UpdateUserRequest
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.responses.UserResponse

/**
 * This class represents the data manager related to Users API
 */
class UserDataManager {

    private val apiManager: ApiManager = ApiManager()

    /**
     * This will call the getUsersVerified method of UserService interface
     * @return an Observable of a list of [UserResponse]
     */
    fun getUsers(): Observable<List<UserResponse>> {
        return apiManager.getUserService().getUsersVerified()
    }

    /**
     * This will call the getUserById method of UserService interface
     * @return an Observable of [UserResponse]
     */
    fun getUserById(userId: Int): Observable<UserResponse> {
        return apiManager.getUserService().getUserById(userId)
    }

    /**
     * This will call the getUser method of UserService interface
     * @return an Observable of [UserResponse]
     */
    fun getUser(): Observable<UserResponse> {
        return apiManager.getUserService().getUser()
    }

    /**
     * This will call the updateUser method of UserService interface
     * @return an Observable of [CustomResponse]
     */
    fun updateUser(updateUserRequest: UpdateUserRequest): Observable<CustomResponse> {
        return apiManager.getUserService().updateUser(updateUserRequest)
    }
}
