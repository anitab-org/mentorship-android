package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.PreferenceManager

/**
 * This class represents the [ViewModel] component used for the Login Activity
 */
class LoginViewModel : BaseViewModel() {

    override val TAG = LoginViewModel::class.java.simpleName

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()

    /**
     * Used to run the login method of the AuthService
     * @param login a login request object containing the credentials
     */
    fun login(login: Login) =
        observe(authDataManager.login(login), { preferenceManager.putAuthToken(it.authToken) })
}
