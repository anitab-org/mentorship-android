package org.systers.mentorship.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mentorship_tasks.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.view.adapters.TasksAdapter
import org.systers.mentorship.viewmodels.TasksViewModel

@SuppressLint("ValidFragment")
/**
 * The fragment is responsible for showing the all mentorship tasks
 * and achievements. It also allows to add new tasks.
 */
class TasksFragment(private var mentorshipRelation: Relationship) : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [TasksFragment]
         */
        fun newInstance(mentorshipRelation: Relationship) = TasksFragment(mentorshipRelation)

        private val TAG = TasksFragment::class.java.simpleName
    }

    private val appContext = MentorshipApplication.getContext()

    private lateinit var taskViewModel: TasksViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_mentorship_tasks

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(mentorshipRelation))
                .get(TasksViewModel::class.java)

        taskViewModel.relationFetchSuccessful.observe(this, Observer { successful ->
            if (successful != null) {
                if (successful) {
                    if (taskViewModel.tasksList.isEmpty()) {
                        tvNoTask.visibility = View.VISIBLE
                        rvTasks.visibility = View.GONE
                    } else {
                        rvTasks.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = TasksAdapter(activity!!, taskViewModel.tasksList, ::markTask, ::deleteTask)
                        }
                        tvNoTask.visibility = View.GONE
                    }
                } else {
                    view?.let {
                        Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        fabAddItem.setOnClickListener { showNewTaskDialog() }

        taskViewModel.getTasks(taskViewModel.mentorshipRelation.id)

    }

    /**
     * The function creates an AlertDialog through which new tasks can be added
     */
    private fun showNewTaskDialog() {
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        builder.setTitle(appContext.getString(R.string.add_new_task))
        val dialogLayout = inflater.inflate(R.layout.dialog_add_task, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddTask)
        builder.setView(dialogLayout)
        builder.setPositiveButton(appContext.getString(R.string.save)) { _, _ ->
            val taskName: String = editText.text.toString()
            taskViewModel.addTask(taskName)
        }
        builder.setNegativeButton(appContext.getString(R.string.cancel)) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private fun markTask(taskId: Int, isChecked: Boolean) {
        Log.d(TAG, "markTask(): taskId: $taskId, isChecked: $isChecked")
        taskViewModel.updateTask(taskId, isChecked)
    }

    private fun deleteTask(taskId: Int) {
        Log.d(TAG, "deleteTask(): taskId: $taskId")
        taskViewModel.deleteTask(taskId)
    }
}