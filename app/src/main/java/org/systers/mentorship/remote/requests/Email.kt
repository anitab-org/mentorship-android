package org.systers.mentorship.remote.requests

/**
 * This data class represents the data required to resends an email to the
 * registered user
 * @param email represents the email of the user
 */
data class Email(
        var email: String
)