package org.systers.mentorship.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.LoginRequest
import org.systers.mentorship.remote.responses.LoginResponse
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.PreferenceManager
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * Created by murad on 7/26/18.
 */
class LoginViewModel : ViewModel() {

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()
    
    fun login(@NonNull loginRequest: LoginRequest) {
        authDataManager.login(loginRequest)
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(object : DisposableObserver<LoginResponse>() {
                    override fun onNext(loginResponse: LoginResponse) {
                        preferenceManager.putAuthToken(loginResponse.authToken)
                    }
                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> //TODO: Show no internet error
                                Log.d("LoginViewModel", "IOException")
                            is TimeoutException -> //TODO: Show timeout exception
                                Log.d("LoginViewModel", "TimeoutException")
                            is HttpException -> {
                                val error = CommonUtils.getErrorResponse(throwable)
                                Log.d("LoginViewModel", error.message)
                                //TODO: Show custom error message error.message
                            }
                            else -> //TODO: Show general error message
                                Log.d("LoginViewModel", "general error message")
                        }
                    }
                    override fun onComplete() {
                    }
                })
    }
}
