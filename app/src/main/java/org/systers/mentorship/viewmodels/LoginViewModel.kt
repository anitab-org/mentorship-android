package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.remote.responses.AuthToken
import org.systers.mentorship.utils.PreferenceManager

/**
 * This class represents the [ViewModel] component used for the Login Activity
 */
class LoginViewModel : BaseViewModel() {

    override var TAG = LoginViewModel::class.java.simpleName

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()

    /**
     * Will be used to run the login method of the AuthService
     * @param login a login request object containing the credentials
     */
    @SuppressLint("CheckResult")
    fun login(@NonNull login: Login) {
        authDataManager.login(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer {
                    preferenceManager.putAuthToken((it as AuthToken).authToken)
                })
    }
}
