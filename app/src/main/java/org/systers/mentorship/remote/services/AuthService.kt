package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.remote.responses.AuthToken
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * This interface describes the methods related to Authentication REST API
 */
interface AuthService {

    /**
     * This function allows a user to login into the system
     * @param login data required to login a user
     * @return an observable instance of the [AuthToken]
     */
    @POST("login")
    fun login(@Body login: Login): Observable<AuthToken>

    /**
     * This function allows a user to sign up into the system
     * @param register data required to register a user
     * @return an observable instance of the [CustomResponse]
     */
    @POST("register")
    fun register(@Body register: Register): Observable<CustomResponse>
}
