package org.systers.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notification_list_item.view.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Notification
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.activities.NotificationsActivity

class NotificationsAdapter(
        private val notificationsList: List<Notification> = mutableListOf()
) : RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NotificationViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.notification_list_item, parent, false))

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val item = notificationsList[position]

        with(holder.itemView) {
            tvNotificationTitle.text = item.title
            tvNotificationMessage.text = item.message

            val hours = (System.currentTimeMillis() - item.creationTime) / (1000 * 60 * 60)
            tvNotificationTime.text = when {
                hours < 1 -> "Less than an hour ago"
                hours < 2 -> "An hour ago"
                hours < 24 -> "${hours.toInt()} hours ago"
                hours < 48 -> "A day ago"
                hours < 24 * 7 -> "${(hours / 24).toInt()} days ago"
                hours < 24 * 7 * 2 -> "A week ago"
                hours < 24 * 7 * 4 -> "${(hours / (24 * 7)).toInt()} weeks ago"
                hours > 24 * 7 * 4 -> "More than a month ago"
                else -> context.getString(R.string.error_something_went_wrong)
            }

            ivNotificationImage.setImageResource(when (item.type) {
                0 -> R.drawable.ic_add_primary_24dp
                1 -> R.drawable.ic_accept_green_24dp
                2 -> R.drawable.ic_close_red_24dp
                3 -> R.drawable.ic_task_primary_24dp
                4 -> R.drawable.ic_achievement_green_24dp
                else -> R.mipmap.ic_launcher
            })

            btnNotificationSeeDetails.setOnClickListener {
                NotificationsActivity.instance.onBackPressed()
                when {
                    item.type <= 2 -> MainActivity.showFragment = 4
                    item.type <= 4 -> MainActivity.showFragment = 2
                    else -> Toast.makeText(context, R.string.error_something_went_wrong, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount() = notificationsList.size

    /**
     * This class holds a view for each item of the Tasks list
     * @param itemView represents each view of Tasks list
     */
    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}