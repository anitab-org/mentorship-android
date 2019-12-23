package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.PreferenceManager
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] component used for the Login Activity
 */
class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.java.simpleName

    private val appContext = MentorshipApplication.getContext()
    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String


    /**
     * Runs the login method of the AuthService
     * @param login a login request object containing the credentials
     */
    fun login(login: Login) = viewModelScope.launch {
        try {
            val token = authDataManager.login(login)
            preferenceManager.putAuthToken(token.authToken)

            successful.value = true
        } catch (throwable: Exception) {
            when (throwable) {
                is IOException -> {
                    message = appContext.getString(R.string.error_please_check_internet)
                }
                is TimeoutException -> {
                    message = appContext.getString(R.string.error_request_timed_out)
                }
                is HttpException -> {
                    message = CommonUtils.getErrorResponse(throwable).message
                }
                else -> {
                    message = appContext.getString(R.string.error_something_went_wrong)
                    Log.e(TAG, throwable.localizedMessage)
                }
            }
            successful.value = false
        }
    }
}
