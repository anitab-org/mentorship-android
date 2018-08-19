package org.systers.mentorship.view.adapters

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_member_item.view.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.responses.UserResponse
import org.systers.mentorship.utils.NON_VALID_VALUE_REPLACEMENT

/**
 * This class represents the adapter that fills in each view of the Members recyclerView
 * @param usersList list of users to show
 * @param openDetailFunction function to be called when an item from Members list is clicked
 */
class MembersAdapter (
        private val usersList: List<UserResponse>,
        private val openDetailFunction: (memberId: Int) -> Unit
) : RecyclerView.Adapter<MembersAdapter.MembersViewHolder>() {

    val context = MentorshipApplication.getContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder =
            MembersViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.list_member_item, parent, false)
            )

    override fun onBindViewHolder(@NonNull holder: MembersViewHolder, position: Int) {
        val item = usersList[position]
        val itemView = holder.itemView ?: return

        itemView.tvName.text = item.name
        itemView.tvMentorshipAvailability.text = getMentorshipAvailabilityText(item.isAvailableToMentor, item.needsMentoring)

        val userInterests = item.interests
        val validText = if (userInterests.isNullOrBlank()) NON_VALID_VALUE_REPLACEMENT else userInterests
        val keyText = context.getString(R.string.interests)
        val keyValueText = "$keyText: $validText"
        itemView.tvInterests.text = keyValueText

        itemView.setOnClickListener { openDetailFunction(item.id) }
    }

    override fun getItemCount(): Int = usersList.size

    /**
     * This class holds a view for each item of the Members list
     * @param itemView represents each view of Members list
     */
    class MembersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun getMentorshipAvailabilityText(availableToMentor: Boolean, needMentoring: Boolean): String {

        if (availableToMentor && needMentoring)
            return context.getString(R.string.available_to_mentor_and_mentee)
        if (availableToMentor)
            return context.getString(R.string.only_available_to_mentor)
        if (needMentoring)
            return context.getString(R.string.only_available_to_mentee)
        else
            return context.getString(R.string.not_available_to_mentor_or_mentee)
    }
}
