package org.anitab.mentorship.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.anitab.mentorship.R

@BindingAdapter("isAvailableToMentor", "isMentoringNeeded", requireAll = true)
fun TextView.showAvailability(availableToMentor: Boolean?, needMentoring: Boolean?) {
    text = if (availableToMentor != null && needMentoring != null) {
        if (availableToMentor && needMentoring) resources.getString(R.string.available_to_mentor_and_mentee)
        else if (availableToMentor) resources.getString(R.string.only_available_to_mentor)
        else if (needMentoring) resources.getString(R.string.only_available_to_mentee)
        else resources.getString(R.string.not_available_to_mentor_or_mentee)
    } else resources.getString(R.string.not_available_to_mentor_or_mentee)
}

@BindingAdapter("setInterest")
fun TextView.showInterests(interests: String?) {
    text = interests?.let { "Interests: $it" } ?: "Interests: --"
}