package org.systers.mentorship.models

/**
 * This [Enum] represents all the states of a notification.
 * All these values represents the action done on the mentorship request which generated the notification.
 *
 * ACCEPTED  - when the mentorship request is accepted.
 * REJECTED  - when the mentorship request is rejected.
 * RETRIEVED - when a new mentorship request is received.
 * */
enum class NotificationState(val state: Int) {
    ACCEPTED(1),
    REJECTED(2),
    RECEIVED(3)
}
