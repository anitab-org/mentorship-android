package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.LoginRequest
import org.systers.mentorship.remote.requests.RegisterRequest
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.responses.LoginResponse

/**
 * Created by murad on 7/26/18.
 */
class AuthDataManager {

    private val apiManager: ApiManager = ApiManager()

    /**
     * This will call the login method of AuthService interface
     * @param loginRequest The login request body containing the credentials
     * @return an Observable LoginResponse
     */
    fun login(loginRequest: LoginRequest): Observable<LoginResponse> {
        return apiManager.getAuthService().login(loginRequest)
    }

    /**
     * This will call the register method of AuthService interface
     * @param registerRequest The registration request body containing
     *                        the registration required fields
     * @return an Observable CustomResponse
     */
    fun register(registerRequest: RegisterRequest): Observable<CustomResponse> {
        return apiManager.getAuthService().register(registerRequest)
    }
}
