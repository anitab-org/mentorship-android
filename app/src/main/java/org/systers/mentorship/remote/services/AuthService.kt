package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.responses.AuthToken
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by murad on 7/26/18.
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
    fun register(@Body register: Register) : Observable<CustomResponse>
}
