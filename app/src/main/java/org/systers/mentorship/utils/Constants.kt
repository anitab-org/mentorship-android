package org.systers.mentorship.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {

    const val ITEMS_PER_PAGE = 20
    const val TOTAL_REQUEST_TABS = 3
    const val MEMBER_USER_ID = "member_user_id"
    const val REQUEST_LIST = "request_list"
    const val REQUEST_EMPTY_LIST_TEXT = "request_empty_list_text"
    const val RELATIONSHIP_EXTRA = "relationship_extra"
    const val DELETE_REQUEST_RESULT_ID = 1000
    const val REQUEST_ID = "request_id"
    // filter function in MembersFragment
    const val FILTER_REQUEST_CODE = 8000
    const val FILTER_MAP = "filter_map"
    const val SORT_KEY = "sort_key"
    const val NEED_MENTORING_KEY = "need_mentoring"
    const val AVAILABLE_TO_MENTOR_KEY = "available_to_mentor"
    const val INTERESTS_KEY = "interests"
    const val LOCATION_KEY = "location"
    const val SKILLS_KEY = "skills"
    const val TOKEN_EXPIRED_EXTRA = "JsonAuthTokenExpired"
    const val DB_VERSION = 1

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= 23) {
            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

                else -> return false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}
