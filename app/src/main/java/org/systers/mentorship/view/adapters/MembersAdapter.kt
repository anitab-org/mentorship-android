package org.systers.mentorship.view.adapters

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_member_item.view.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.User
import org.systers.mentorship.utils.NON_VALID_VALUE_REPLACEMENT
import java.util.*

/**
 * This class represents the adapter that fills in each view of the Members recyclerView
 * @param userList list of users to show
 * @param openDetailFunction function to be called when an item from Members list is clicked
 */
class MembersAdapter(
        private val userList: List<User>,
        private val openDetailFunction: (memberId: Int) -> Unit
) : RecyclerView.Adapter<MembersAdapter.MembersViewHolder>() {

    private val filteredUserList = userList.toMutableList()
    private val context = MentorshipApplication.getContext()
    private var filter = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder =
            MembersViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.list_member_item, parent, false)
            )

    override fun onBindViewHolder(@NonNull holder: MembersViewHolder, position: Int) {
        val item = filteredUserList[position]
        val itemView = holder.itemView

        // the beginning of  searchQuery  -> end = startIndex + length
        val startIndex = item.name?.toLowerCase(Locale.getDefault())?.indexOf(filter) ?: 0
        val spannableStringBuilder = SpannableStringBuilder(item.name ?: "")

        // the text is already bold, so we just change color to the primary color
        val foregroundColorSpan = ForegroundColorSpan(Color.rgb(137, 36, 104))

        // change colors of characters
        spannableStringBuilder.setSpan(foregroundColorSpan, startIndex, startIndex + filter.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        itemView.tvName.text = spannableStringBuilder
        itemView.tvMentorshipAvailability.text = getMentorshipAvailabilityText(item.isAvailableToMentor, item.needsMentoring)

        val userInterests = item.interests
        val validText = if (userInterests.isNullOrBlank()) NON_VALID_VALUE_REPLACEMENT else userInterests
        val keyText = context.getString(R.string.interests)
        val keyValueText = "$keyText: $validText"
        itemView.tvInterests.text = keyValueText

        itemView.setOnClickListener { openDetailFunction(item.id!!) }
    }

    override fun getItemCount(): Int = filteredUserList.size

    fun filterByName(name: String) {
        filter = name.toLowerCase(Locale.getDefault())
        filteredUserList.clear()
        filteredUserList.addAll(userList.filter {
            it.name?.toLowerCase(Locale.getDefault())?.contains(
                    name.toLowerCase(Locale.getDefault())) ?: name.isEmpty()
        })
        notifyDataSetChanged()
    }

    /**
     * This class holds a view for each item of the Members list
     * @param itemView represents each view of Members list
     */
    class MembersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun getMentorshipAvailabilityText(availableToMentor: Boolean?, needMentoring: Boolean?): String {

        if (availableToMentor != null && needMentoring != null) {
            return if (availableToMentor && needMentoring) context.getString(R.string.available_to_mentor_and_mentee)
            else if (availableToMentor) context.getString(R.string.only_available_to_mentor)
            else if (needMentoring) context.getString(R.string.only_available_to_mentee)
            else context.getString(R.string.not_available_to_mentor_or_mentee)
        }

        return context.getString(R.string.not_available_to_mentor_or_mentee)
    }
}
