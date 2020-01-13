package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

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
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successful.postValue(false)
            } else {
                if (customResponse != null) {
                    message = customResponse.message
                } else {
                    successful.postValue(false)
                }
            }
        }
    }
}
