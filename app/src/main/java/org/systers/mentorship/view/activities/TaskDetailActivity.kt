package org.systers.mentorship.view.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_task_detail.*
import kotlinx.android.synthetic.main.fragment_mentorship_tasks.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.Task
import org.systers.mentorship.view.adapters.TasksAdapter
import org.systers.mentorship.viewmodels.CommentsViewModel
import org.systers.mentorship.viewmodels.SendRequestViewModel

class TaskDetailActivity: BaseActivity() {

    companion object {
        const val TASK = "TASK"
        const val MENTORSHIP_RELATION = "MENTORSHIP_RELATION"
    }

    lateinit var task: Task
    lateinit var mentorshipRelationship: Relationship

    private val commentsViewModel by lazy {
        ViewModelProviders.of(this).get(CommentsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        supportActionBar?.title = getString(R.string.task_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        task = intent.getParcelableExtra(TASK) as Task
        mentorshipRelationship = intent.getParcelableExtra(MENTORSHIP_RELATION) as Relationship

        TaskDescription.text = task.description

        commentsViewModel.successfulGet.observe(this, Observer {

            successful ->
            if (successful != null) {
                if (successful) {
                    if (commentsViewModel.commentsList.isEmpty()) {
                        rvTaskComments.visibility = GONE
                        tvNoComments.visibility = VISIBLE
                    } else {
                        // comments list is not empty
                    }
                } else {
                    Snackbar.make(this.getRootView(), commentsViewModel.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
}