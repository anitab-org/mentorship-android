package org.systers.mentorship.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.EditText
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mentorship_tasks.*
import kotlinx.android.synthetic.main.task_list_item.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.view.activities.MainActivity
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
        val TAG = TasksFragment::class.java.simpleName
    }

    val appContext = MentorshipApplication.getContext()
    private val taskViewModel by lazy {
        ViewModelProviders.of(this).get(TasksViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_mentorship_tasks;

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskViewModel.successful.observe(this, Observer {
            successful ->
            if (successful != null) {
                if (successful) {
                    if (taskViewModel.tasksList.isEmpty()) {
                        tvNoTask.visibility = View.VISIBLE
                        rvTasks.visibility = View.GONE
                    } else {
                        rvTasks.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = TasksAdapter(taskViewModel.tasksList, markTask)
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

        fabAddItem.setOnClickListener {
            showDialog()
        }

        taskViewModel.getTasks(mentorshipRelation.id)

    }

    /**
     * The function creates a dialog box through whoch new tasks can be added
     */
    fun showDialog() {
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        builder.setTitle(appContext.getString(R.string.add_new_task))
        val dialogLayout = inflater.inflate(R.layout.dialog_add_task, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddTask)
        builder.setView(dialogLayout)
        builder.setPositiveButton(appContext.getString(R.string.save)) { dialogInterface, i ->
            val newTask: String = editText.text.toString()
            taskViewModel.addTask(newTask)
        }
        builder.setNegativeButton(appContext.getString(R.string.cancel)) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private val markTask: (Int) -> Unit = { taskId ->
        cbTask.isChecked
        taskViewModel.updateTask(taskId, cbTask.isChecked)
    }
}