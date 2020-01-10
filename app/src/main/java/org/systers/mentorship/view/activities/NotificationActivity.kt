package org.systers.mentorship.view.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notification.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.view.activities.MainActivity.Companion.notificationList
import org.systers.mentorship.view.adapters.NotificationAdapter

/**
 * This activity will show all the notifications of the user.
 */
class NotificationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        supportActionBar?.let{
            it.title = getString(R.string.notifications)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        rvNotificationsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NotificationAdapter(notificationList, openRequestDetail)
        }

    }

    private val openRequestDetail: (Relationship) -> Unit =
        {requestDetail ->
            //TODO: Start the RequestDetail activity of {requestDetail}
        }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
