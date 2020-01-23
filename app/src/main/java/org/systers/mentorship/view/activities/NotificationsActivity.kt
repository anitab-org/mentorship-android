package org.systers.mentorship.view.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notifications.*
import org.systers.mentorship.R
import org.systers.mentorship.room.NotificationsDatabase
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.adapters.NotificationsAdapter

class NotificationsActivity : BaseActivity() {

    companion object {
        lateinit var instance: NotificationsActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        instance = this

        setTitle((R.string.notifications))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val notifications = NotificationsDatabase.getInstance(this)
                .notificationDao()
                .getNotifications()
                .reversed()

        with(rvNotifications) {
            layoutManager = LinearLayoutManager(context)
            adapter = NotificationsAdapter(notifications)
        }

        // reset count of unread notifications
        PreferenceManager().resetUnreadNotifications()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_stay, R.anim.anim_slide_to_right)
    }

}
