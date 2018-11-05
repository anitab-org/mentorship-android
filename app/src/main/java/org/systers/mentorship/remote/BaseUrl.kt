package org.systers.mentorship.remote

import org.systers.mentorship.BuildConfig

/**
 * Created by murad on 7/26/18.
 */
object BaseUrl {

    private const val PROTOCOL_HTTPS = "http://"
    private const val EB_REGION = ".eu-central-1.elasticbeanstalk.com/"

    private const val PRODUCTION_URL = "systers-mentorship"
    private const val STAGING_URL = "systers-mentorship-dev"

    val getBaseUrl: String
        get() = if (BuildConfig.DEBUG) {
            "$PROTOCOL_HTTPS$STAGING_URL$EB_REGION"
        } else {
            "$PROTOCOL_HTTPS$PRODUCTION_URL$EB_REGION"
        }
}
