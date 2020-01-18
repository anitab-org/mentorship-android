package org.systers.mentorship.viewmodels

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants

class SocialLoginViewModel : ViewModel() {

    private val context = MentorshipApplication.getContext()
    private val gso by lazy {
        GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build()
    }
    private val twitterProvider = OAuthProvider.newBuilder("twitter.com")
    private val googleSignInClient: GoogleSignInClient by lazy { GoogleSignIn.getClient(context, gso) }
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()
    private val loginManager: LoginManager = LoginManager.getInstance()

    val auth = FirebaseAuth.getInstance()

    val successful = MutableLiveData<Boolean>()
    var message = ""

    init {
        /*
            We need to assure that user is logged out of all accounts before
            she/he can sign in again.
       */
        auth.signOut()
        googleSignInClient.signOut()
        loginManager.logOut()

        loginManager.registerCallback(callbackManager, facebookCallback())
    }

    private fun facebookCallback() = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            val credential = FacebookAuthProvider.getCredential(loginResult.accessToken.token)
            firebaseAuth(credential)
        }

        override fun onCancel() {
            successful.value = false
            message = ""
        }

        override fun onError(error: FacebookException) {
            error.printStackTrace()
            successful.value = false
            message = context.getString(R.string.error_something_went_wrong)
        }
    }

    fun loginWithGoogle(activity: Activity) =
            activity.startActivityForResult(
                    googleSignInClient.signInIntent,
                    Constants.RC_SIGN_IN_GOOGLE)

    fun loginWithFacebook(activity: Activity) =
            loginManager.logInWithReadPermissions(activity, listOf("email", "public_profile"))

    fun loginWithTwitter(activity: Activity) =
            auth.startActivityForSignInWithProvider(activity, twitterProvider.build())
                    .addOnSuccessListener {
                        successful.value = true
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                        successful.value = false
                        message = context.getString(R.string.error_something_went_wrong)
                    }

    fun firebaseAuth(credential: AuthCredential) =
            auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    successful.value = true
                } else {
                    it.exception?.printStackTrace()
                    successful.value = false
                    message = context.getString(R.string.auth_failed)
                }
            }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
            callbackManager.onActivityResult(requestCode, resultCode, data)

}