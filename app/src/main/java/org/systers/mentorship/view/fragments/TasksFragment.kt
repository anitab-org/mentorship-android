package org.systers.mentorship.view.fragments

import android.animation.Animator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_mentorship_tasks.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.view.adapters.TasksAdapter
import org.systers.mentorship.viewmodels.TaskViewModelFactory
import org.systers.mentorship.viewmodels.TasksViewModel


/**
 * The fragment is responsible for showing the all mentorship tasks
 * and achievements. It also allows to add new tasks.
 */
@SuppressLint("ValidFragment")
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

        val tasksAdapter = TasksAdapter(baseActivity, markTask, deleteTask)
        val achievementsAdapter = TasksAdapter(baseActivity, markTask, deleteTask)

        rvTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tasksAdapter
        }

        rvAchievements.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = achievementsAdapter
        }

        taskViewModel = ViewModelProviders.of(this, TaskViewModelFactory(mentorshipRelation))
            .get(TasksViewModel::class.java)

        taskViewModel.getSuccessful.observe(this, Observer { successful ->
            if (successful == true) {
                if (taskViewModel.tasks.isEmpty()) {
                    tvNoTask.visibility = VISIBLE
                    tvNoAchievements.visibility = VISIBLE
                    rvTasks.visibility = GONE
                    rvAchievements.visibility = GONE
                } else {
                    tvNoTask.visibility = GONE
                    tvNoAchievements.visibility = GONE
                    rvTasks.visibility = VISIBLE
                    rvAchievements.visibility = VISIBLE

                    tasksAdapter.tasks = taskViewModel.tasks.filter { !it.isDone }
                    achievementsAdapter.tasks = taskViewModel.tasks.filter { it.isDone }
                }
            } else {
                view?.let {
                    Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        taskViewModel.createSuccessful.observe(this, Observer { successful ->
            if (successful == true) {
                view?.let {
                    Snackbar.make(it, R.string.task_added, Snackbar.LENGTH_LONG).show()
                }
            } else {
                view?.let {
                    Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        taskViewModel.completeSuccessful.observe(this, Observer { successful ->
            if (successful == true) {
                view?.let {
                    Snackbar.make(it, R.string.task_completed, Snackbar.LENGTH_LONG).show()
                }
            } else {
                view?.let {
                    Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        taskViewModel.deleteSuccessful.observe(this, Observer { successful ->
            if (successful == true) {
                view?.let {
                    Snackbar.make(it, R.string.task_deleted, Snackbar.LENGTH_LONG).show()
                }
            } else {
                view?.let {
                    Snackbar.make(it, taskViewModel.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        taskViewModel.getTasks()

        fabAddItem.setOnClickListener {
            showDialog()
        }

        // Enables animations when child view's property changes
        clRootTasks.layoutTransition = LayoutTransition().apply {
            enableTransitionType(LayoutTransition.CHANGING)
        }

        btnExpandTasks.setOnClickListener {
            with(rvTasks) {
                visibility = if (visibility == VISIBLE) GONE else VISIBLE
            }

            val currentRotation = btnExpandTasks.rotation

            ObjectAnimator.ofFloat(
                btnExpandTasks, "rotation", currentRotation, currentRotation + 180
            ).apply {
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator?) {
                        btnExpandTasks.isClickable = false
                    }

                    override fun onAnimationEnd(animator: Animator?) {
                        btnExpandTasks.isClickable = true
                    }

                    override fun onAnimationRepeat(animator: Animator?) {}

                    override fun onAnimationCancel(animator: Animator?) {}
                })
                duration = 300
                start()
            }
        }

        btnExpandAchievements.setOnClickListener {
            with(rvAchievements) {
                visibility = if (visibility == VISIBLE) GONE else VISIBLE
            }

            val currentRotation = btnExpandAchievements.rotation

            ObjectAnimator.ofFloat(
                btnExpandAchievements, "rotation", currentRotation, currentRotation + 180
            ).apply {
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator?) {
                        btnExpandAchievements.isClickable = false
                    }

                    override fun onAnimationEnd(animator: Animator?) {
                        btnExpandAchievements.isClickable = true
                    }


                    override fun onAnimationRepeat(animator: Animator?) {}

                    override fun onAnimationCancel(animator: Animator?) {}
                })
                duration = 300
                start()
            }
        }
    }

    /**
     * Creates a dialog box through which new tasks can be added
     */
    private fun showDialog() {
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        builder.setTitle(appContext.getString(R.string.add_new_task))
        val dialogLayout = inflater.inflate(R.layout.dialog_add_task, null, false)
        val editText = dialogLayout.findViewById<EditText>(R.id.etAddTask)
        builder.setView(dialogLayout)
        builder.setPositiveButton(appContext.getString(R.string.save)) { _, _ ->
            val newTask: String = editText.text.toString()
            taskViewModel.createTask(newTask)
        }
        builder.setNegativeButton(appContext.getString(R.string.cancel)) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private val markTask: (Int) -> Unit = { taskId ->
        taskViewModel.completeTask(taskId)
    }

    private val deleteTask: (Int) -> Unit = { taskId ->
        taskViewModel.deleteTask(taskId)
    }
}
