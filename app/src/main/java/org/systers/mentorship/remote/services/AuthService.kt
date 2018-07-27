package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.remote.requests.LoginRequest
import org.systers.mentorship.remote.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by murad on 7/26/18.
 */
interface AuthService {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Observable<LoginResponse>
}