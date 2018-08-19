package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.remote.requests.LoginRequest
import org.systers.mentorship.remote.requests.RegisterRequest
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.remote.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by murad on 7/26/18.
 */
interface AuthService {

    /**
     * This function allows a user to login into the system
     * @param loginRequest data required to login a user
     * @return an observable instance of the [LoginResponse]
     */
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Observable<LoginResponse>

    /**
     * This function allows a user to sign up into the system
     * @param registerRequest data required to register a user
     * @return an observable instance of the [CustomResponse]
     */
    @POST("register")
    fun register(@Body registerRequest: RegisterRequest) : Observable<CustomResponse>
}
