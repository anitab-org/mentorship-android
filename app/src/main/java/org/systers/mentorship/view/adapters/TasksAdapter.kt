package org.systers.mentorship.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_list_item.view.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Task

/**
 * This class represents the adapter that fills in each view of the Tasks recyclerView
 * @param tasksList list of tasks taken up by the mentee
 * @param markTask function to be called when an item from Tasks list is clicked
 */
class TasksAdapter(
        private val context: Context,
        private val tasksList: List<Task>,
        private val markTask: (taskId: Int, isChecked: Boolean) -> Unit
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val TAG = "TasksAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
            TaskViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.task_list_item, parent, false))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasksList[position]
        val itemView = holder.itemView

        itemView.cbTask.text = task.description
        itemView.cbTask.isChecked = task.isDone
        itemView.cbTask.setOnCheckedChangeListener { _, checked ->
            if (!checked) return@setOnCheckedChangeListener

            val thisTask = tasksList[holder.adapterPosition]

            AlertDialog.Builder(context)
                    .setTitle(R.string.complete_task)
                    .setMessage("Are you sure that you want to complete \"${thisTask.description}\"?" +
                            " This action cannot be undone.")
                    .setPositiveButton(R.string.mark_as_completed) { _, _ ->
                        markTask(thisTask.id, true)
                    }
                    .setNeutralButton(R.string.cancel) { _, _ ->
                        if (checked) {
                            itemView.cbTask.isChecked = false
                        }
                    }
                    .show()
        }
    }

    override fun getItemCount(): Int = tasksList.size

    /**
     * This class holds a view for each item of the Tasks list
     * @param itemView represents each view of Tasks list
     */
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}