package org.systers.mentorship.models

data class DashBoardResponse(
        val asMentor: AsMentor,
        val asMentee: AsMentor,
        val tasksDone: List<Task>,
        val tasksTodo: List<Task>
)

data class AsMentor(
        val sent: RequestStatus,
        val received: RequestStatus
)

data class RequestStatus(
        val accepted: List<Relationship>,
        val rejected: List<Relationship>,
        val completed: List<Relationship>,
        val cancelled: List<Relationship>,
        val pending: List<Relationship>
)



