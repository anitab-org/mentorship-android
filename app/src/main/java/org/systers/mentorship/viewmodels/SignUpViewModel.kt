package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Register

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class SignUpViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName

    private val authDataManager: AuthDataManager = AuthDataManager()

    /**
     * Will be used to run the register method of the AuthService
     * @param register a registration request object containing the a user's registration fields
     */
    fun register(register: Register) = observe(authDataManager.register(register))
}
