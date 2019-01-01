package org.systers.mentorship.remote.datamanager

import io.reactivex.Observable
import io.reactivex.Single
import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.Email
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.remote.responses.AuthToken
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * Created by murad on 7/26/18.
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
    /**
     * This will call the resendEmail method of AuthService interface
     * @param email The resend email request body containing
     *                        the resend email required fields
     * @return an Single CustomResponse
     */
    fun resendEmail(email: Email): Single<CustomResponse> {
        return apiManager.authService.resendEmail(email)
    }
}
