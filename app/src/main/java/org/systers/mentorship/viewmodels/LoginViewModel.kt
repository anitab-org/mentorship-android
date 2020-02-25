package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.annotations.NonNull
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.PreferenceManager

/**
 * This class represents the [ViewModel] component used for the Login Activity
 */
class LoginViewModel : ViewModel() {

    var tag = LoginViewModel::class.java.simpleName!!

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Will be used to run the login method of the AuthService
     * @param login a login request object containing the credentials
     */
    @SuppressLint("CheckResult")
    fun login(@NonNull login: Login) {
        authDataManager.login(login)
                .process { authToken, throwable ->
                    when (throwable) {
                        null -> {
                            when (authToken) {
                                null -> {
                                    successful.postValue(false)
                                }
                                else -> {
                                    preferenceManager.putAuthToken(authToken.accessToken)
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

