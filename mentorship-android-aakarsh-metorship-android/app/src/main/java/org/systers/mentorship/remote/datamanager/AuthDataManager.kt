package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.remote.responses.AuthToken
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the data manager related to Authentication API
 */
class AuthDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call the login method of AuthService interface
     * @param login The login request body containing the credentials
     * @return an Observable AuthToken
     */
    fun login(login: Login): Observable<AuthToken> {
        return apiManager.authService.login(login)
    }

    /**
     * This will call the register method of AuthService interface
     * @param register The registration request body containing
     *                        the registration required fields
     * @return an Observable CustomResponse
     */
    fun register(register: Register): Observable<CustomResponse> {
        return apiManager.authService.register(register)
    }
}
