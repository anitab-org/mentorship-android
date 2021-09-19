package org.systers.mentorship.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Login
import org.systers.mentorship.utils.CommonUtils
import org.systers.mentorship.utils.PreferenceManager

/**
 * This class represents the [ViewModel] component used for the Login Activity
 */
class LoginViewModel : ViewModel() {

    var tag = LoginViewModel::class.java.simpleName

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val authDataManager: AuthDataManager = AuthDataManager()

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _buttonEnabled = MutableLiveData(false)
    val buttonEnabled: LiveData<Boolean> = _buttonEnabled

    private val _successful = MutableLiveData(false)
    val successful: LiveData<Boolean> = _successful

    val message = MutableLiveData<String>()

    /**
     * Will be used to run the login method of the AuthService
     * @param login a login request object containing the credentials
     */
    fun login(login: Login) {
       _buttonEnabled.value = false
        viewModelScope.launch {
            try {
                preferenceManager.putAuthToken(authDataManager.login(login).accessToken)
                _successful.value = true
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                _successful.value = false
               _buttonEnabled.value = true
            }
        }
    }

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
        _buttonEnabled.value = !username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        _buttonEnabled.value = !username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
    }

    fun onButtonClick() {
        message.value = ""
        login(Login(username.value!!, password.value!!))
    }
}
