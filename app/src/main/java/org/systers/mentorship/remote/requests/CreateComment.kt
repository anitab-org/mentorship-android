package org.systers.mentorship.remote.requests

import com.google.gson.annotations.SerializedName

data class CreateComment(@SerializedName("comment") val comment: String)
