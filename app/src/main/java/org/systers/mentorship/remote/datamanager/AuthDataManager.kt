package org.systers.mentorship.remote.datamanager

import org.systers.mentorship.remote.ApiManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.requests.Register

/**
 * This class represents the data manager related to Authentication API
 */
class AuthDataManager {

    private val apiManager = ApiManager.instance

    /**
     * This will call the login method of AuthService interface
     * @param login The login request body containing the credentials
     */
    suspend fun login(login: Login) = apiManager.authService.login(login)

    /**
     * This will call the register method of AuthService interface
     * @param register The registration request body containing
     *                        the registration required fields
     */
    suspend fun register(register: Register) = apiManager.authService.register(register)
}
