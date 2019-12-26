package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.remote.responses.AuthToken
import retrofit2.http.POST

interface RefreshService {

    /**
     * This function allows a user to get a new auth token
     * @param refreshToken refresh token
     * @return an observable instance of the [AuthToken]
     */
    @POST("refresh")
    fun refreshToken(): Observable<AuthToken>

}