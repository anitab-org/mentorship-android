package org.systers.mentorship.remote

import org.systers.mentorship.BuildConfig

/**
 * Object to keep settings such as URL parts, regions, s3 bucket names.
 */
object BaseUrl {
    private const val PROTOCOL_HTTPS = "http://"
    private const val PRODUCTION_URL = "systers-mentorship"
    private const val DEVELOPMENT_URL = "systers-mentorship-dev"
    private const val FULL_DEVELOPMENT_URL_LOCAL = "http://10.0.2.2:5000/"
    private const val EB_REGION = ".eu-central-1.elasticbeanstalk.com/"

    private const val PROTOCOL_S3 = "s3://"
    private const val S3_BUCKET_NAME = "systers-mentorship/"
    private const val S3_PRODUCTION_DIRECTORY = "prod/"
    private const val S3_DEVELOPMENT_DIRECTORY = "dev/"

    val apiBaseUrl: String
        get() = if (BuildConfig.DEBUG) {

            /*
             * IDE will complain that "this condition is always true".
             * No, it's not! It depends on the build type.
             */
            if (BuildConfig.BUILD_TYPE == "debug_local") {
                FULL_DEVELOPMENT_URL_LOCAL
            } else "$PROTOCOL_HTTPS$DEVELOPMENT_URL$EB_REGION"

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
