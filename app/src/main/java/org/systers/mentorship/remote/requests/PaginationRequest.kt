package org.systers.mentorship.remote.requests

import com.squareup.moshi.JsonClass

/**
 * This data class represents all data necessary to paginate data
 * @param page the current page to be accessed
 * @param per_page data limit size for per page
 */
@JsonClass(generateAdapter = true)
data class PaginationRequest (val page: Int, val per_page: Int){
    val pagination = mapOf("page" to page.toString(), "per_page" to per_page.toString())
}

