package org.systers.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_member_item.view.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.NON_VALID_VALUE_REPLACEMENT

/**
 * This class represents the adapter that fills in each view of the Members recyclerView
 * @param openDetailFunction function to be called when an item from Members list is clicked
 */
class MembersAdapter constructor(
        private val openDetailFunction: (memberId: Int) -> Unit
) : RecyclerView.Adapter<MembersAdapter.MembersViewHolder>() {

    val context = MentorshipApplication.getContext()

    var filter: Filter = Filter()
        set(value) {
            field = value

            var newList: List<User> = initialUsers.toList()

            // If filtering options are enabled, apply them
            if (filter.needsMentoring || filter.availableToMentor) {
                newList = newList
                        .filter { it.needsMentoring == filter.needsMentoring }
                        .filter { it.isAvailableToMentor == filter.availableToMentor }
            }

            if (filter.skills.isNotBlank()) {
                newList = newList.filter {
                    it.skills?.contains(filter.skills.trim(), ignoreCase = true) ?: false
                }
            }

            currentUsers = newList.sorted(sort)
        }

    var sort: Sort = Sort.CreationTimeDescending
        set(newSort) {
            field = newSort

            val newList = when (sort) {
                Sort.NameAscending -> {
                    currentUsers.toMutableList().sortedBy {
                        it.name?.toLowerCase()
                    }
                }
                Sort.NameDescending -> {
                    currentUsers.toMutableList().sortedByDescending {
                        it.name?.toLowerCase()
                    }
                }
                Sort.CreationTimeAscending -> {
                    val userListAscending = initialUsers.reversed()
                    userListAscending
                }
                Sort.CreationTimeDescending -> initialUsers
            }

            currentUsers = newList
        }

    /**
     * Sorts current list using passed [sort]
     */
    private fun List<User>.sorted(sort: Sort): List<User> {
        return when (sort) {
            Sort.NameAscending -> {
                toMutableList().sortedBy {
                    it.name?.toLowerCase()
                }
            }
            Sort.NameDescending -> {
                toMutableList().sortedByDescending {
                    it.name?.toLowerCase()
                }
            }
            Sort.CreationTimeAscending -> {
                val userListAscending = this.reversed()
                userListAscending
            }
            Sort.CreationTimeDescending -> this
        }
    }

    /**
     * Original user list, sorted by creation time in descending order
     * **Rationale:** This methods exists only because the list returned by the API is sorted
     * by creation time in descending order, but the User objects itself don't
     * have any information about creation time.
     */
    var initialUsers: List<User> = arrayListOf()

    /**
     * Currently displayed user list, with sorting and filtering applied.
     */
    var currentUsers: List<User> = arrayListOf()
        set(newUsers) {
            val diffCallback = MembersDiffCallback(currentUsers, newUsers)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            field = newUsers
            diffResult.dispatchUpdatesTo(this)
        }

    private fun getMentorshipAvailabilityText(availableToMentor: Boolean?, needMentoring: Boolean?): String {

        if (availableToMentor != null && needMentoring != null) {
            return if (availableToMentor && needMentoring) context.getString(R.string.available_to_mentor_and_mentee)
            else if (availableToMentor) context.getString(R.string.only_available_to_mentor)
            else if (needMentoring) context.getString(R.string.only_available_to_mentee)
            else context.getString(R.string.not_available_to_mentor_or_mentee)
        }

        return context.getString(R.string.not_available_to_mentor_or_mentee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder =
            MembersViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.list_member_item, parent, false)
            )

    override fun onBindViewHolder(@NonNull holder: MembersViewHolder, position: Int) {
        val item = currentUsers[position]
        val itemView = holder.itemView

        itemView.tvName.text = item.name
        itemView.tvMentorshipAvailability.text = getMentorshipAvailabilityText(item.availableToMentor, item.needMentoring)

        val userInterests = item.interests
        val validText = if (userInterests.isNullOrBlank()) NON_VALID_VALUE_REPLACEMENT else userInterests
        val keyText = context.getString(R.string.interests)
        val keyValueText = "$keyText: $validText"
        itemView.tvInterests.text = keyValueText

        itemView.setOnClickListener { openDetailFunction(item.id!!) }
    }

    override fun getItemCount(): Int = currentUsers.size

    /**
     * Holds a view for each item of the Members list
     * @param itemView represents each view of Members list
     */
    class MembersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * Represents available sorting methods.
     */
    enum class Sort(val number: Int) {
        NameAscending(0), NameDescending(1), CreationTimeAscending(2), CreationTimeDescending(3);

        companion object {
            private val map = values().associateBy(Sort::number)
            /**
             * Returns Sort enum corresponding to the passed integer [type]
             */
            fun fromInt(type: Int) = map[type]
        }
    }

    /**
     * Represents available properties which the user list can be filtered by.
     */
    data class Filter(val needsMentoring: Boolean = false, val availableToMentor: Boolean = false, val skills: String = "")
}

class MembersDiffCallback(private val oldList: List<User>, private val newList: List<User>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

}
