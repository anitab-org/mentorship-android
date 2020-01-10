package org.systers.mentorship.view.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_notification_item.view.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Notification
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.NotificationState

/**
 * This class represents the adapter that fills in each view of the Notifications recyclerView
 * @param notificationList list of notifications to show
 * @param openRequestDetail function to be called when an item from Notifications list is clicked
 */
class NotificationAdapter(
        private val notificationList: List<Notification>,
        private val openRequestDetail: (requestDetail: Relationship) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    val context = MentorshipApplication.getContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
            NotificationViewHolder(
                LayoutInflater.from(parent.context)
                              .inflate(R.layout.list_notification_item, parent, false))


    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val itemView = holder.itemView

        /**
         * Filling the values in the notification container.
         * */
        var header = notificationList[position].header
        var description = notificationList[position].description
        var message = notificationList[position].message
        var requestDetail = notificationList[position].requestDetail

        itemView.tvHeader.text = header
        itemView.tvDescription.text = description
        itemView.tvMessage.text = message

        /**
         * Setting the color of the view according to Notification State
         * */
        var viewColor = if (notificationList[position].state == NotificationState.ACCEPTED) Color.GREEN
                              else if (notificationList[position].state == NotificationState.REJECTED) Color.RED
                              else Color.MAGENTA

        itemView.cvContainer.strokeColor = viewColor
        itemView.tvHeader.setTextColor(viewColor)

        itemView.setOnClickListener {
            openRequestDetail(requestDetail)
        }
    }

    override fun getItemCount(): Int = notificationList.size

    class NotificationViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

}
