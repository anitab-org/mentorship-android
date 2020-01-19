package org.systers.mentorship.view.adapters

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        private val taskList: MutableList<Task>,
        private val markTask: (taskId: Int) -> Unit
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    val context = MentorshipApplication.getContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
            TaskViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.task_list_item, parent, false))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = taskList[position]
        val itemView = holder.itemView

        itemView.cbTask.text = item.description
        itemView.cbTask.isChecked = item.isDone
        itemView.cbTask.setOnCheckedChangeListener { checkBox, isChecked ->
            // prevents us from opening dialogBox aggain on cancel
            if (isChecked == item.isDone)
                return@setOnCheckedChangeListener

            if (!item.isDone)
                AlertDialog.Builder(checkBox.context)
                        .setTitle(context.getString(R.string.complete_task_title))
                        .setMessage(context.getString(R.string.complete_task_message))
                        .setPositiveButton(context.getString(R.string.complete)) { _, _ ->
                            markTask(item.id)
                        }
                        .setNegativeButton(R.string.cancel) { _, _ ->
                            checkBox.isChecked = false
                        }
                        .setOnCancelListener {
                            checkBox.isChecked = false
                        }
                        .show()
            else
                AlertDialog.Builder(checkBox.context)
                        .setTitle(context.getString(R.string.delete_task_title))
                        .setMessage(context.getString(R.string.delete_task_message))
                        .setPositiveButton(context.getString(R.string.delete)) { _, _ ->
                            markTask(item.id)
                        }
                        .setNegativeButton(R.string.cancel) { _, _ ->
                            checkBox.isChecked = true
                        }
                        .setOnCancelListener {
                            checkBox.isChecked = true
                        }
                        .show()
        }
    }

    override fun getItemCount(): Int = taskList.size

    /**
     * This class holds a view for each item of the Tasks list
     * @param itemView represents each view of Tasks list
     */
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}