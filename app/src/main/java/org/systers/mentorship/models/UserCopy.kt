package org.systers.mentorship.models

/*
 *  This class is needed to deep copy the User class.
 *  All the arguments are the same, but all of them can't be null.
 */
data class UserCopy(
        var id: Int = -1,
        var username: String = "",
        var name: String = "",
        var email: String = "",
        var bio: String = "",
        var location: String = "",
        var occupation: String = "",
        var organization: String = "",
        var interests: String = "",
        var skills: String = "",
        var needsMentoring: Boolean = true,
        var isAvailableToMentor: Boolean = true,
        var slackUsername: String = ""
)