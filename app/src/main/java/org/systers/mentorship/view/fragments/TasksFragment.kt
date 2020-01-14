package org.systers.mentorship.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mentorship_tasks.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.Task
import org.systers.mentorship.remote.requests.TaskRequest
import org.systers.mentorship.view.adapters.AchievementsAdapter
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

    private lateinit var taskViewModel: TasksViewModel

    private var tasks = mutableListOf<Task>()
    private var achievements = mutableListOf<Task>()

    override fun getLayoutResourceId(): Int = R.layout.fragment_mentorship_tasks;

    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TasksAdapter(tasks, markTask)
        }

        rvAchievements.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AchievementsAdapter(achievements)
        }

        taskViewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
        taskViewModel.successfulGet.observe(this, Observer {
            successful ->
            if (successful != null) {
                if (successful) {

                    tasks.clear()
                    achievements.clear()

                    /**
                     * Separating uncompleted tasks and achievements from the list of all tasks.
                     */
                    for (task in taskViewModel.tasksList) {
                        if (task.isDone){
                            achievements.add(task)
                        } else {
                            tasks.add(task)
                        }
                    }

                    if (tasks.size>0){
                        updateView(rvTasks, tvNoTask, ivArrowTasks, View.VISIBLE, View.GONE)
                    } else {
                        updateView(rvTasks, tvNoTask, ivArrowTasks, View.GONE, View.VISIBLE)
                    }

                    if (achievements.size>0){
                        updateView(rvAchievements, tvNoAchievements, ivArrowAchievements, View.VISIBLE, View.GONE)
                    } else {
                        updateView(rvAchievements, tvNoAchievements, ivArrowAchievements, View.GONE, View.VISIBLE)
                    }

                } else {
                    view?.let {
                        Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        taskViewModel.successfulPost.observe(this, Observer {
            successful ->
            if (successful != null) {
                if (successful) {
                    Toast.makeText(context, taskViewModel.message, Toast.LENGTH_LONG).show()
                    taskViewModel.getTasks(mentorshipRelation.id)
                } else {
                    view?.let {
                        Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        taskViewModel.successfulPut.observe(this, Observer {
            successful ->
            if (successful != null) {
                if (successful) {
                    Toast.makeText(context, taskViewModel.message, Toast.LENGTH_LONG).show()
                    taskViewModel.getTasks(mentorshipRelation.id)
                } else {
                    view?.let {
                        Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })

        taskViewModel.getTasks(mentorshipRelation.id)

        fabAddItem.setOnClickListener {
            showDialog()
        }

        ivArrowTasks.setOnClickListener {
            toggleRecyclerView(rvTasks, tvNoTask, ivArrowTasks, tasks)
        }

        ivArrowAchievements.setOnClickListener {
            toggleRecyclerView(rvAchievements, tvNoAchievements, ivArrowAchievements, achievements)
        }

    }

    /**
     * This function update the views in the Tasks tab.
     * */
    private fun updateView(recyclerView: RecyclerView, textView: AppCompatTextView, ivArrow: AppCompatImageView,
                           rvVisibilty: Int, tvVisibilty: Int) {
        recyclerView.visibility = rvVisibilty
        textView.visibility = tvVisibilty
        recyclerView.adapter?.notifyDataSetChanged()
        ivArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
    }

    /**
     * This function toggles the To do and Achievements recyclerView.
     */
    @SuppressLint("NewApi")
    private fun toggleRecyclerView(recyclerView: RecyclerView, textView: AppCompatTextView, ivArrow: AppCompatImageView, tasksList: MutableList<Task>) {
        val imagePositionDownArrow = ivArrow.drawable.constantState == resources.getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp, context?.theme).constantState

        if (imagePositionDownArrow){
            ivArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
            recyclerView.visibility = View.GONE
            textView.visibility = View.GONE
        } else {
            ivArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
            if (tasksList.size>0) recyclerView.visibility = View.VISIBLE
            else textView.visibility = View.VISIBLE
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
            val task = TaskRequest(
                    description = editText.text.toString()
            )
            taskViewModel.addTask(mentorshipRelation.id,task)

        }
        builder.setNegativeButton(appContext.getString(R.string.cancel)) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private val markTask: (Int) -> Unit = { taskId ->
        taskViewModel.completeTask(mentorshipRelation.id, taskId)
    }
}