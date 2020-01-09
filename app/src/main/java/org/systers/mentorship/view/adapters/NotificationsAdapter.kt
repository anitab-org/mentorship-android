package org.systers.mentorship.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notification_list_item.view.*
import org.systers.mentorship.R
import org.systers.mentorship.db.AppDatabase
import org.systers.mentorship.db.Notification
import java.text.SimpleDateFormat
import java.util.*

class NotificationsAdapter(
    private var items: List<Notification>,
    private val appDatabase: AppDatabase,
    private val clickFun: (notification: Notification) -> Unit
) : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    fun setData(newItems: List<Notification>) {
        val diffCallback = NotificationsDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_list_item, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val layout = holder.itemView
        val textTitle = holder.itemView.tvNotificationTitle
        val textMessage = holder.itemView.tvNotificationMessage
        val buttonDelete = holder.itemView.btnDeleteNotification
        val textDate = holder.itemView.tvNotificationTimestamp

        textTitle.text = item.title
        textMessage.text = item.message

        val sdf = SimpleDateFormat("dd MMMM yyyy hh:mm")
        val formattedDate = sdf.format(Date(item.createdTimestamp))
        textDate.text = formattedDate

        layout.setOnClickListener {
            clickFun(items[holder.adapterPosition])
        }

        buttonDelete.setOnClickListener {
            appDatabase.notificationDao()
                .deleteNotification(items[holder.adapterPosition].createdTimestamp)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

class NotificationsDiffCallback(private val old: List<Notification>, val new: List<Notification>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]
}
