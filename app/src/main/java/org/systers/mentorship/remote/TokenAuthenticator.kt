package org.systers.mentorship.remote

import android.widget.Toast
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants.STATUS_CODE_UNAUTHORISED
import org.systers.mentorship.view.activities.SettingsActivity.Companion.logout

/**
 * Represents a custom HTTP Requests Authenticator.
 * */
class TokenAuthenticator : Authenticator {

    override fun authenticate(route: Route, response: Response): Request? {
        /**
         * If the user's token has expired, it will logout the user from the app.
         * */
        if ( response.code() == STATUS_CODE_UNAUTHORISED ) {
            logout()
            val context = MentorshipApplication.getContext()
            Toast.makeText(context, R.string.token_expired, Toast.LENGTH_LONG).show()
        }
        return null
    }
}