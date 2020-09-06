package org.systers.mentorship.view.activities

import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_task_detail.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.models.Task

class TaskDetailActivity: BaseActivity() {

    companion object {
        const val TASK = "TASK"
        const val MENTORSHIP_RELATION = "MENTORSHIP_RELATION"
    }

    lateinit var task: Task
    lateinit var mentorshipRelationship: Relationship

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        supportActionBar?.title = getString(R.string.task_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        task = intent.getParcelableExtra(TASK) as Task
        mentorshipRelationship = intent.getParcelableExtra(MENTORSHIP_RELATION) as Relationship

        TaskDescription.text = task.description
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