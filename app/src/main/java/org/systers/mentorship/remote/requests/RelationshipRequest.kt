package org.systers.mentorship.remote.requests

import com.squareup.moshi.JsonClass

/**
 * This data class represents all data necessary to send a mentorship relation request
 * @param mentor_id represents mentor user id
 * @param mentee_id represents mentee user id
 * @param notes represents a description of the mentorship relation
 * @param end_date represents end date of the mentorship relation
 */
@JsonClass(generateAdapter = true)
data class RelationshipRequest (
        val mentor_id: Int,
        val mentee_id: Int,
        val notes: String,
        val end_date: Long)
