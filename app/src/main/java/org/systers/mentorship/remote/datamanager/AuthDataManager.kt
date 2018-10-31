package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.responses.AuthToken

/**
 * Created by murad on 7/26/18.
 */
class AuthDataManager {

    private val apiManager: ApiManager = ApiManager()

    /**
     * This will call the login method of AuthService interface
     * @param login The login request body containing the credentials
     * @return an Observable AuthToken
     */
    fun login(login: Login): Observable<AuthToken> {
        return apiManager.getAuthService().login(login)
    }

    /**
     * This will call the register method of AuthService interface
     * @param register The registration request body containing
     *                        the registration required fields
     * @return an Observable CustomResponse
     */
    fun register(register: Register): Observable<CustomResponse> {
        return apiManager.getAuthService().register(register)
    }
}
