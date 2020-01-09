package org.systers.mentorship.remote

import org.systers.mentorship.BuildConfig

/**
 * Object to keep settings such as URL parts, regions, s3 bucket names.
 */
object BaseUrl {

    private const val PROTOCOL_HTTPS = "http://"
    private const val PRODUCTION_URL = "systers-mentorship"
    private const val DEVELOPMENT_URL = "systers-mentorship-dev"
    private const val EB_REGION = ".eu-central-1.elasticbeanstalk.com/"

    /*
        Strings for storing calls paths that are used to delete file containing cash of that request
     */
    val GET_USER_URL = "${apiBaseUrl}user"
    val GET_HOME_STATS_URL = "${apiBaseUrl}home"
    val GET_CURRENT_RELATION_URL = "${apiBaseUrl}mentorship_relations/current"
    val GET_USERS_URL = "${apiBaseUrl}users/verified"
    val GET_REQUESTS_URL = "${apiBaseUrl}mentorship_relations"

    private const val PROTOCOL_S3 = "s3://"
    private const val S3_BUCKET_NAME = "systers-mentorship/"
    private const val S3_PRODUCTION_DIRECTORY = "prod/"
    private const val S3_DEVELOPMENT_DIRECTORY = "dev/"

    val apiBaseUrl: String
        get() = if (BuildConfig.DEBUG) {
            "$PROTOCOL_HTTPS$DEVELOPMENT_URL$EB_REGION"
        } else {
            "$PROTOCOL_HTTPS$PRODUCTION_URL$EB_REGION"
        }

    val s3BaseUrl: String
        get() = if (BuildConfig.DEBUG) {
            "$PROTOCOL_S3$S3_BUCKET_NAME$S3_DEVELOPMENT_DIRECTORY"
        } else {
            "$PROTOCOL_S3$S3_BUCKET_NAME$S3_PRODUCTION_DIRECTORY"
        }
}
