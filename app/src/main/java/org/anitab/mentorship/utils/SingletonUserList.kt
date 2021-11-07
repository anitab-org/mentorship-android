package org.anitab.mentorship.utils

import org.anitab.mentorship.models.User

/**
 * This Singleton class is used to share large user list data from MemberFragment to
 * SearchMembersFragment.
 */

object SingletonUserList {
    var userList: ArrayList<User> = arrayListOf()
}
