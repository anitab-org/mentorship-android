package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.LoginRequest
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

}
