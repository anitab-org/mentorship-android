package org.systers.mentorship.remote.services

import io.reactivex.Observable
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * This interface describes the methods related to Users REST API
 */
interface UserService {

    /**
     * This function returns all users of the system
     * @return an observable instance of a list of [User]s
     */
    @GET("users")
    fun getUsers(): Observable<List<User>>

    /**
     * This function returns all users, with email verified,
     * of the system with requested pagination info
     * @return an observable instance of a list of [User]s
     */
    @GET("users/verified")
    fun getVerifiedUsers(@QueryMap pagination: Map<String, String>): Observable<List<User>>

    /**
     * This function returns all users, with email verified, of the system
     * @return an observable instance of a list of [User]s
     */
    @GET("users/verified")
    fun getVerifiedUsers(): Observable<List<User>>

    /**
     * This function returns a user's public profile of the system
     * @return an observable instance of the [User]
     */
    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Observable<User>

    /**
     * This function returns the current user profile
     * @return an observable instance of the [User]
     */
    @GET("user")
    fun getUser(): Observable<User>

    /**
     * This function updates the current user's profile
     * @return an observable instance of the [CustomResponse]
     */
    @PUT("user")
    fun updateUser(@Body user: User): Observable<CustomResponse>

    /**
     * This function updates the current user password
     * @return an observable instance of the [CustomResponse]
     */
    @PUT("user/change_password")
    fun updatePassword(@Body changePassword: ChangePassword): Observable<CustomResponse>

    /**
     * This function gets the current user's home screen statistics
     * @return an observable instance of [HomeStatistics]
     */
    @GET("home")
    fun getHomeStats(): Observable<HomeStatistics>
}
