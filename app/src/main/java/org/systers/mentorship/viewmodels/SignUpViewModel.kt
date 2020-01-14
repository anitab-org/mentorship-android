package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.annotations.NonNull
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Register

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class SignUpViewModel : ViewModel() {

    var TAG = SignUpViewModel::class.java.simpleName

    private val authDataManager: AuthDataManager = AuthDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Will be used to run the register method of the AuthService
     * @param register a registration request object containing the a user's registration fields
     */
    @SuppressLint("CheckResult")
    fun register(@NonNull register: Register) {
        authDataManager.register(register).process { customResponse, throwable ->
            if (customResponse != null) {
                successful.postValue(true)
                message = customResponse.message
            } else if (throwable != null) {
                successful.postValue(false)
                message = throwable.message.toString()
            }
        }
    }
}
