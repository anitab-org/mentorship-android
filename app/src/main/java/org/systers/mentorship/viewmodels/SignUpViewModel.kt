package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.annotations.NonNull
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Register

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class SignUpViewModel : ViewModel() {

    private val authDataManager: AuthDataManager = AuthDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Will be used to run the register method of the AuthService
     * @param register a registration request object containing the a user's registration fields
     */
    @SuppressLint("CheckResult")
    fun register(@NonNull register: Register) {
        authDataManager.register(register)
                .process { customResponse, throwable ->
                    when (throwable) {
                        null -> {
                            when (customResponse) {
                                null -> {
                                    successful.postValue(false)
                                }
                                else -> {
                                    message = customResponse.message
                                    successful.postValue(true)
                                }
                            }
                        }
                        else -> {
                            message = throwable.localizedMessage
                            successful.postValue(false)
                        }
                    }
                }
    }
}

