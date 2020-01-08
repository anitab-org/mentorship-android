package org.systers.mentorship.remote

import android.content.Intent
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.utils.Constants.ERROR_CODE_TOKEN_EXPIRED
import org.systers.mentorship.utils.Constants.TOKEN_EXPIRED
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.activities.LoginActivity

class TokenAuthenticator : Authenticator {

    val context = MentorshipApplication.getContext()

    override fun authenticate(route: Route, response: Response): Request? {
        if (response.code() == ERROR_CODE_TOKEN_EXPIRED) {
            //TODO: Add refresh token functionality

            // logout user and redirect her/him to LoginActivity
            PreferenceManager().clear()
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(TOKEN_EXPIRED, true)
            context.startActivity(intent)
        }
        return null
    }

}