package org.systers.mentorship.remote.requests

/**
 * This data class represents all data necessary to create a task for a relation between two users
 * @param description represents description of the task
 */
data class TaskRequest(
        val description: String
)