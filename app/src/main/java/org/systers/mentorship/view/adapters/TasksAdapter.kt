package org.systers.mentorship.view.adapters

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.task_list_item.view.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Task


/**
 * This class represents the adapter that fills in each view of the Tasks recyclerView
 * @param taskstsList list of tasks taken up by the mentee
 * @param markTask function to be called when an item from Tasks list is clicked
 */
class TasksAdapter(
        private val context: Context,
        private val tasksList: List<Task>,
        private val markTask: (taskId: Int, isChecked: Boolean) -> Unit
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
            TaskViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.task_list_item, parent, false))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = tasksList[position]
        val itemView = holder.itemView
        itemView.cbTask.isChecked = item.isDone
        itemView.cbTask.text = item.description
        itemView.cbTask.setOnCheckedChangeListener{_, checked ->
            if (checked)
            {
                val builder = AlertDialog.Builder(context)
                builder.setTitle(R.string.task_update_title)
                builder.setMessage(context.getString(R.string.task_update_message) + item.description + context.getString(R.string.task_update_message2))
                builder.setPositiveButton(R.string.task_update_confirm) { _, _ ->
                    markTask(item.id, true)
                }
                builder.setNegativeButton(R.string.cancel) { dialog, _ ->
                    itemView.cbTask.isChecked = false
                    dialog.cancel()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun getItemCount(): Int = tasksList.size

    /**
     * This class holds a view for each item of the Tasks list
     * @param itemView represents each view of Tasks list
     */
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}