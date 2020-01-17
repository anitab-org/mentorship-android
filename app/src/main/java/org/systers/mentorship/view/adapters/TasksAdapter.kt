package org.systers.mentorship.view.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_list_item.view.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Task


/**
 * This class represents the adapter that fills in each view of the Tasks recyclerView
 * @param context Context required by the adapter to work properly
 * @param tasks list of tasks taken up by the mentee
 * @param completeTask method to be called when a task on the task list is checked
 * @param deleteTask method to be called when a task on the task list is deleted
 */
class TasksAdapter(
    private val context: Context,
    private val completeTask: (taskId: Int) -> Unit,
    private val deleteTask: (taskId: Int) -> Unit
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    var tasks: List<Task> = arrayListOf()
        set(newTasks) {
            val diffCallback = MembersDiffCallback(tasks, newTasks)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            field = newTasks
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        val itemView = holder.itemView
        val checkBox = itemView.cbTask
        val deleteButton = itemView.btnDeleteTask

        checkBox.text = task.description
        checkBox.isChecked = task.isDone
        checkBox.isEnabled = !task.isDone

        checkBox.setOnCheckedChangeListener { _, checked ->
            if (!checked) {
                return@setOnCheckedChangeListener
            }

            val thisTask = tasks[holder.adapterPosition]

            AlertDialog.Builder(context).setTitle(R.string.complete_task)
                .setMessage(context.getString(R.string.complete_task_are_you_sure, thisTask.description))
                .setPositiveButton(R.string.complete_task) { _, _ ->
                    itemView.cbTask.isEnabled = false
                    completeTask(thisTask.id)
                }
                .setNeutralButton(R.string.cancel) { _, _ ->
                    if (checked) {
                        itemView.cbTask.isChecked = false
                    }
                }
                .show()
        }

        deleteButton.setOnClickListener {
            val thisTask = tasks[holder.adapterPosition]

            AlertDialog.Builder(context).setTitle(R.string.delete_task)
                .setMessage(context.getString(R.string.delete_task_are_you_sure, thisTask.description))
                .setPositiveButton(R.string.delete) { _, _ ->
                    deleteTask(thisTask.id)
                }
                .setNeutralButton(R.string.cancel) { _, _ -> }
                .show()
        }
    }

    override fun getItemCount(): Int = tasks.size

    /**
     * This class holds a view for each item of the Tasks list
     * @param itemView represents each view of Tasks list
     */
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class MembersDiffCallback(
        private val oldList: List<Task>, private val newList: List<Task>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}
