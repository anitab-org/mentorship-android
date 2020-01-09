package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notifications.*
import org.systers.mentorship.R
import org.systers.mentorship.db.AppDatabase
import org.systers.mentorship.db.Notification
import org.systers.mentorship.view.adapters.NotificationsAdapter

class NotificationsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val adapter = NotificationsAdapter(
            items = arrayListOf(),
            appDatabase = AppDatabase.getInstance(this),
            clickFun = ::onNotificationClick
        )
        rvNotifications.layoutManager = LinearLayoutManager(this)
        rvNotifications.adapter = adapter

        AppDatabase.getInstance(this).notificationDao().findAll().observe(this, Observer {
            if (it.isNullOrEmpty()) {
                rvNotifications.visibility = GONE
                tvNoNotifications.visibility = VISIBLE
            } else {
                adapter.setData(it)
                rvNotifications.visibility = VISIBLE
                tvNoNotifications.visibility = GONE
            }
        })
    }

    private fun onNotificationClick(notification: Notification) {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtras(Bundle().apply {
                putString(START_FRAGMENT, notification.type.symbolicName)
            })
        })
    }
}
