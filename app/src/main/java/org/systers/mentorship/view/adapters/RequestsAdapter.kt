package org.systers.mentorship.view.adapters

import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.databinding.ListRequestsItemBinding
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.DATE_FORMAT
import org.systers.mentorship.utils.convertUnixTimestampIntoStr
import org.systers.mentorship.utils.setTextViewStartingWithBoldSpan

/**
 * This class represents the adapter that fills in each view of the Requests recyclerView
 * @param requestsList list of request and relation to show
 * @param openDetailFunction function to be called when an item from Requests list is clicked
 */
class RequestsAdapter (
        private val requestsList: List<Relationship>,
        private val openDetailFunction: (requestDetail: Relationship) -> Unit
) : RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder>() {

    val context = MentorshipApplication.getContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder {
        return RequestsViewHolder(ListRequestsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false)
        )
    }

    override fun onBindViewHolder(@NonNull holder: RequestsViewHolder, position: Int) {
        val item = requestsList[position]
        val itemBinding = holder.itemBinding

        setTextViewStartingWithBoldSpan(itemBinding.tvMentorName, context.getString(R.string.mentor), item.mentor.name)
        setTextViewStartingWithBoldSpan(itemBinding.tvMenteeName, context.getString(R.string.mentee), item.mentee.name)
        setTextViewStartingWithBoldSpan(itemBinding.tvEndDate, context.getString(R.string.end_date), convertUnixTimestampIntoStr(item.endDate, DATE_FORMAT))
        setTextViewStartingWithBoldSpan(itemBinding.tvNotes, context.getString(R.string.notes), item.notes)
        itemBinding.tvCreationDate.text = convertUnixTimestampIntoStr(item.creationDate, DATE_FORMAT)

        // switch arrow direction according to the user that sent a mentorship request
        if (!item.sentByMe) {
            itemBinding.ivActionUserIndicator.rotation = 180f
        }

        holder.itemView.setOnClickListener { openDetailFunction(item) }
    }

    override fun getItemCount(): Int = requestsList.size

    /**
     * This class holds a view for each item of the Requests list
     * @param itemView represents each view of Requests list
     */
    class RequestsViewHolder(val itemBinding: ListRequestsItemBinding) : RecyclerView.ViewHolder(itemBinding.root)
}
