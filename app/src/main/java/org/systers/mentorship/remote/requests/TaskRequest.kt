package org.systers.mentorship.remote.requests

/**
 * This data class represents all the data required to create a new task
 * @param description represents description of the task
 * */
data class TaskRequest (
        val description: String
)