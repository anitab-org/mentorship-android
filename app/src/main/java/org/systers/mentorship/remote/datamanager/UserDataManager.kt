package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Users API
 */
class UserDataManager {

    private val apiManager: ApiManager = ApiManager()

    /**
     * This will call the getUsersVerified method of UserService interface
     * @return an Observable of a list of [User]
     */
    fun getUsers(): Observable<List<User>> {
        return apiManager.getUserService().getUsersVerified()
    }

    /**
     * This will call the getUserById method of UserService interface
     * @return an Observable of [User]
     */
    fun getUserById(userId: Int): Observable<User> {
        return apiManager.getUserService().getUserById(userId)
    }

    /**
     * This will call the getUser method of UserService interface
     * @return an Observable of [User]
     */
    fun getUser(): Observable<User> {
        return apiManager.getUserService().getUser()
    }

    /**
     * This will call the updateUser method of UserService interface
     * @return an Observable of [CustomResponse]
     */
    fun updateUser(user: User): Observable<CustomResponse> {
        return apiManager.getUserService().updateUser(user)
    }
}
