package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.remote.responses.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This interface describes the methods related to Users REST API
 */
interface UserService {

    /**
     * This function returns all users of the system
     * @return an observable instance of a list of [UserResponse]s
     */
    @GET("users")
    fun getUsers(): Observable<List<UserResponse>>

    /**
     * This function returns all users, with email verified, of the system
     * @return an observable instance of a list of [UserResponse]s
     */
    @GET("users/verified")
    fun getUsersVerified(): Observable<List<UserResponse>>

    /**
     * This function returns a user's public profile of the system
     * @return an observable instance of the [UserResponse]
     */
    @GET("users/{userId}")
    fun getUserById(@Path("userId") userId: Int): Observable<UserResponse>
}
