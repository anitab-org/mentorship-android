package org.systers.mentorship.utils

import android.preference.PreferenceManager
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.models.User

object PreferenceUtils {

    private val pm = PreferenceManager.getDefaultSharedPreferences(MentorshipApplication.instance)

    private const val ID = "id"
    private const val USERNAME = "username"
    private const val NAME = "name"
    private const val EMAIL = "email"
    private const val BIO = "bio"
    private const val LOCATION = "location"
    private const val OCCUPATION = "occupation"
    private const val ORGANIZATION = "organization"
    private const val INTERESTS = "interests"
    private const val SKILLS = "skills"
    private const val NEED_MENTORING = "need_mentoring"
    private const val AVAILABLE_TO_MENTOR = "available_to_mentor"
    private const val SLACK_USERNAME = "slack_username"


    var id: Int?
        get() = pm.getInt(ID, 0)
        set(value) {
            pm.edit().putInt(ID, value!!).apply()
        }

    var username: String?
        get() = pm.getString(USERNAME, "")
        set(value) {
            pm.edit().putString(USERNAME, value).apply()
        }

    var name: String?
        get() = pm.getString(NAME, "")
        set(value) {
            pm.edit().putString(NAME, value).apply()
        }

    var email: String?
        get() = pm.getString(EMAIL, "")
        set(value) {
            pm.edit().putString(EMAIL, value).apply()
        }

    var bio: String?
        get() = pm.getString(BIO, "")
        set(value) {
            pm.edit().putString(BIO, value).apply()
        }

    var location: String?
        get() = pm.getString(LOCATION, "")
        set(value) {
            pm.edit().putString(LOCATION, value).apply()
        }

    var occupation: String?
        get() = pm.getString(OCCUPATION, "")
        set(value) {
            pm.edit().putString(OCCUPATION, value).apply()
        }

    var organization: String?
        get() = pm.getString(ORGANIZATION, "")
        set(value) {
            pm.edit().putString(ORGANIZATION, value).apply()
        }
    var interest: String?
        get() = pm.getString(INTERESTS, "")
        set(value) {
            pm.edit().putString(INTERESTS, value).apply()
        }
    var skills: String?
        get() = pm.getString(SKILLS, "")
        set(value) {
            pm.edit().putString(SKILLS, value).apply()
        }

    var needMentorting: Boolean?
        get() = pm.getBoolean(NEED_MENTORING, false)
        set(value) {
            pm.edit().putBoolean(NEED_MENTORING, value!!).apply()
        }

    var availableToMentor: Boolean?
        get() = pm.getBoolean(AVAILABLE_TO_MENTOR, false)
        set(value) {
            pm.edit().putBoolean(AVAILABLE_TO_MENTOR, value!!).apply()
        }

    var slackUsername: String?
        get() = pm.getString(SLACK_USERNAME, "")
        set(value) {
            pm.edit().putString(SLACK_USERNAME, value).apply()
        }

    fun setUser(user: User) {
        val preferenceUtils = PreferenceUtils
        preferenceUtils.username = user.username
        preferenceUtils.name = user.name
        if (user.id != null)
            preferenceUtils.id = user.id
        preferenceUtils.email = user.email
        preferenceUtils.bio = user.bio
        preferenceUtils.location = user.location
        preferenceUtils.occupation = user.occupation
        preferenceUtils.organization = user.organization
        preferenceUtils.interest = user.interests
        preferenceUtils.skills = user.skills
        preferenceUtils.needMentorting = user.needMentoring
        preferenceUtils.availableToMentor = user.availableToMentor
        preferenceUtils.slackUsername = user.slackUsername
    }

    fun getUser(): User {
        val pu = PreferenceUtils
        return User(pu.id, pu.username, pu.name, pu.email, pu.bio, pu.location, pu.occupation, pu.organization, pu.interest, pu.skills, pu.needMentorting, pu.availableToMentor, pu.slackUsername)
    }

    fun clearData() {
        pm.edit().clear().apply()
    }
}