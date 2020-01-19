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
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.TaskDescription
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

    private val appContext = MentorshipApplication.getContext()

    private lateinit var taskViewModel: TasksViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_mentorship_tasks

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskViewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
        taskViewModel.relationId = mentorshipRelation.id

        taskViewModel.successful.observe(this, Observer { successful ->
            if (successful != null) {
                if (successful) {
                    val tasks = taskViewModel.taskList.filter { !it.isDone } as MutableList
                    val achievements = taskViewModel.taskList.filter { it.isDone } as MutableList

                    if (tasks.isEmpty()) {
                        tvNoTask.visibility = View.VISIBLE
                        rvTasks.visibility = View.GONE
                        imageView.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
                    } else {
                        imageView.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
                        rvTasks.visibility = View.VISIBLE
                        rvTasks.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = TasksAdapter(tasks) {
                                taskViewModel.updateTask(it, true)
                            }
                        }
                        tvNoTask.visibility = View.GONE
                    }

                    if (achievements.isEmpty()) {
                        tvNoAchievements.visibility = View.VISIBLE
                        rvAchievements.visibility = View.GONE
                        imageView2.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
                    } else {
                        imageView2.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
                        rvAchievements.visibility = View.VISIBLE
                        rvAchievements.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = TasksAdapter(achievements) {
                                taskViewModel.updateTask(it, false)
                            }
                        }
                        tvNoAchievements.visibility = View.GONE
                    }
                } else {
                    view?.let {
                        Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
        taskViewModel.successfulCreate.observe(this, Observer { successful ->
            if (successful != null) {
                view?.let {
                    Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })
        taskViewModel.successfulComplete.observe(this, Observer { successful ->
            if (successful != null) {
                view?.let {
                    Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })
        taskViewModel.successfulDelete.observe(this, Observer { successful ->
            if (successful != null) {
                view?.let {
                    Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        fabAddItem.setOnClickListener {
            showDialog()
        }
        imageView.setOnClickListener {
            it.animate().rotationBy(180f).start()
            rvTasks.visibility = if (rvTasks.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        imageView2.setOnClickListener {
            it.animate().rotationBy(180f).start()
            rvAchievements.visibility = if (rvAchievements.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        taskViewModel.getTasks()

    }

    /**
     * The function creates a dialog box through which new tasks can be added
     */
    private fun showDialog() {
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        builder.setTitle(appContext.getString(R.string.add_new_task))
        val dialogLayout = inflater.inflate(R.layout.dialog_add_task, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddTask)
        builder.setView(dialogLayout)
        builder.setPositiveButton(appContext.getString(R.string.save)) { _, _ ->
            val newTask = TaskDescription(editText.text.toString())
            taskViewModel.addTask(newTask)
        }
        builder.setNegativeButton(appContext.getString(R.string.cancel)) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.show()
    }

}