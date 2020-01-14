package org.systers.mentorship.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mentorship_tasks.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.requests.CreateTask
import org.systers.mentorship.view.adapters.AchievementsAdapter
import org.systers.mentorship.view.adapters.TasksAdapter
import org.systers.mentorship.viewmodels.HomeViewModel
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

    private lateinit var homeViewModel: HomeViewModel
    val appContext = MentorshipApplication.getContext()
    private lateinit var achievementsAdapter: AchievementsAdapter

    private lateinit var taskViewModel: TasksViewModel

    override fun getLayoutResourceId(): Int = R.layout.fragment_mentorship_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        achievementsAdapter = AchievementsAdapter()
        val linearLayoutManager = LinearLayoutManager(context)

        rvAchievements.apply {
            adapter = achievementsAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskViewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
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
                            adapter = TasksAdapter(activity!!, taskViewModel.tasksList, ::markTask)
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

        taskViewModel.createSuccessful.observe(this, Observer {
            createSuccessful ->
            if (createSuccessful != null) {
                if (createSuccessful) {
                    Toast.makeText(context, taskViewModel.createMessage, Toast.LENGTH_LONG).show()
                }
            }
        })

        fabAddItem.setOnClickListener {
            showDialog()
        }

        taskViewModel.getTasks(mentorshipRelation.id)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        with(homeViewModel) {
            userStats.observe(viewLifecycleOwner, Observer { stats ->
                if (stats?.achievements?.isEmpty() != false) {
                    tvNoAchievements.visibility = View.VISIBLE
                    rvAchievements.visibility = View.GONE
                } else {
                    tvNoAchievements.visibility = View.GONE
                    rvAchievements.visibility = View.VISIBLE
                    achievementsAdapter.submitList(stats.achievements)
                }
            })

            message.observe(viewLifecycleOwner, Observer { message ->
                view?.let {
                    Snackbar.make(it, message.toString(), Snackbar.LENGTH_LONG).show()
                }
            })
        }

        imageView.setOnClickListener {
            if (rvTasks.visibility == View.VISIBLE){
                imageView.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
                rvTasks.visibility = View.GONE}
            else{
                imageView.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
                rvTasks.visibility = View.VISIBLE}
        }

        imageView2.setOnClickListener {
            if (rvAchievements.visibility == View.VISIBLE){
                imageView2.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
                rvAchievements.visibility = View.GONE}
            else{
                imageView2.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
                rvAchievements.visibility = View.VISIBLE}
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
            val newTask = CreateTask(description = editText.text.toString())
            taskViewModel.addTask(mentorshipRelation.id, newTask)
        }
        builder.setNegativeButton(appContext.getString(R.string.cancel)) { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private fun markTask(taskId: Int, isChecked: Boolean){
        taskViewModel.updateTask(taskId, mentorshipRelation.id, isChecked)
    }
}