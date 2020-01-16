package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Register
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class SignUpViewModel : BaseViewModel() {

    override var TAG: String = SignUpViewModel::class.java.simpleName
    private val authDataManager: AuthDataManager = AuthDataManager()

    /**
     * Will be used to run the register method of the AuthService
     * @param register a registration request object containing the a user's registration fields
     */
    @SuppressLint("CheckResult")
    fun register(@NonNull register: Register) {
        authDataManager.register(register)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer(
                        next = {
                            message = (it as CustomResponse).message
                            successful.value = true
                        },
                        error = {successful.value = false}
                ))
    }
}
