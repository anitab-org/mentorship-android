package org.systers.mentorship.remote.services

import androidx.lifecycle.LiveData
import org.systers.mentorship.models.HomeStatistics
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.remote.responses.ApiResponse
import org.systers.mentorship.remote.responses.CustomResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * This interface describes the methods related to Users REST API
 */
interface UserService {

    /**
     * This function returns all users of the system
     * @return an instance of a list of [User]s
     */
    @GET("users")
    suspend fun getUsers(): List<User>

    // TODO: Refactor to use only this, remove getVerifiedUsers below
    @GET("users")
    fun getVerifiedUsersLD(): LiveData<ApiResponse<List<User>>>
    /**
     * This function returns all users, with email verified, of the system
     * @return an instance of a list of [User]s
     */
    @GET("users/verified")
    suspend fun getVerifiedUsers(): List<User>

    /**
     * This function returns a user's public profile of the system
     * @return an instance of the [User]
     */
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): User

    /**
     * This function returns the current user profile
     */
    @GET("user")
    suspend fun getUser(): User

    /**
     * This function updates the current user's profile
     */
    @PUT("user")
    suspend fun updateUser(@Body user: User): CustomResponse

    /**
     * This function updates the current user password
     */
    @PUT("user/change_password")
    suspend fun updatePassword(@Body changePassword: ChangePassword): CustomResponse

    /**
     * This function gets the current user's home screen statistics
     */
    @GET("home")
    suspend fun getHomeStats(): HomeStatistics
}
