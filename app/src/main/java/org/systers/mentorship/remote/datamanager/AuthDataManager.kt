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

    fun login(loginRequest: LoginRequest): Observable<LoginResponse> {
        return apiManager.getAuthService().login(loginRequest)
    }

}
