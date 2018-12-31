package org.systers.mentorship.view.fragments

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_mentorship_tasks.*
import kotlinx.android.synthetic.main.task_list_item.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.adapters.TasksAdapter
import org.systers.mentorship.viewmodels.TasksViewModel

/**
 * The fragment is responsible for showing the all mentorship tasks
 * and achievements. It also allows to add new tasks.
 */
class TasksFragment : BaseFragment() {

    companion object {
        /**
         * Creates an instance of [TasksFragment]
         */
        fun newInstance() = TasksFragment()
        val TAG = TasksFragment::class.java.simpleName
    }

    val appContext = MentorshipApplication.getContext()

    private lateinit var taskViewModel: TasksViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_mentorship_tasks;

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskViewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
        taskViewModel.getTasks()

        rvTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TasksAdapter(taskViewModel.taskList, markTask)
        }

        ivAddItem.setOnClickListener {
            showDialog()
        }
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
