package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_notification_layout.*
import org.systers.mentorship.R
import org.systers.mentorship.models.Notification
import org.systers.mentorship.models.NotificationState
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.*

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity: BaseActivity() {

    companion object {
        lateinit var notificationList: MutableList<Notification>
    }

    private var notificationCount = 0
    private var atHome = true

    private val preferenceManager: PreferenceManager = PreferenceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNotifications()

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            showHomeFragment()
        } else {
            atHome = savedInstanceState.getBoolean("atHome")
        }
    }

    /**
     * This method creates three new notifications for testing purpose.
     * */
    private fun initNotifications(){

        notificationList  = mutableListOf()

        var userId = 0
        var header: String
        var description: String
        var message: String
        var requestDetail: Relationship
        var state: NotificationState

        requestDetail = Relationship(0,0,false
                , Relationship.RelationUserResponse(0,"")
                , Relationship.RelationUserResponse(0,"")
                , 0f, 0f, 0f, 0f, 0, "")

        header = "Congratulations!"
        description = "Mentorship Request accepted"
        message = "{User} has accepted your mentorship request"
        state = NotificationState.ACCEPTED
        addNotifications(userId, header, description, message, requestDetail, state)

        header = "Sorry!"
        description = "Mentorship Request rejected"
        message = "{User} has rejected your mentorship request"
        state = NotificationState.REJECTED
        addNotifications(userId, header, description, message, requestDetail, state)

        header = "Wow!"
        description = "Mentorship Request received"
        message = "{User} has send you a mentorship request"
        state = NotificationState.RECEIVED
        addNotifications(userId, header, description, message, requestDetail, state)
    }

    /**
     * This method adds a new notification.
     * */
    private fun addNotifications(userId: Int, header: String, description: String, message: String, requestDetail: Relationship, state: NotificationState) {
        var notification = Notification(
                userId = userId,
                header = header,
                description = description,
                message = message,
                requestDetail = requestDetail,
                state = state
        )
        /**
         * Incrementing the notification count as a new notification is created.
         * */
        notificationCount += 1
        invalidateOptionsMenu()
        notificationList.add(notification)
    }

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(R.id.contentFrame, HomeFragment.newInstance(),
                        R.string.fragment_title_home)
                atHome = true
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                replaceFragment(R.id.contentFrame, ProfileFragment.newInstance(),
                        R.string.fragment_title_profile)
                atHome = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_relation -> {
                replaceFragment(R.id.contentFrame, RelationPagerFragment.newInstance(),
                        R.string.fragment_title_relation)
                atHome = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_members -> {
                replaceFragment(R.id.contentFrame, MembersFragment.newInstance(),
                        R.string.fragment_title_members)
                atHome = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_requests -> {
                replaceFragment(R.id.contentFrame, RequestsFragment.newInstance(),
                        R.string.fragment_title_requests)
                atHome = false
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        var menuItem = menu.findItem(R.id.menu_notifications)
        var actionView = menuItem.actionView

        setupBadge()

        actionView.setOnClickListener {
            onOptionsItemSelected(menuItem)
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        setupBadge()
        return super.onPrepareOptionsMenu(menu)
    }

    /**
     * This method updates the notification badge icon.
     * */
    private fun setupBadge() {
        if(tvNotificationBadge != null) {
            if (notificationCount == 0) {
                tvNotificationBadge.visibility = View.GONE
            } else {
                tvNotificationBadge.text = notificationCount.toString()
                tvNotificationBadge.visibility = View.VISIBLE
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.menu_notifications -> {
                    notificationCount = 0
                    invalidateOptionsMenu()
                    startActivity(Intent(this, NotificationActivity::class.java))
                    true
                }
                R.id.menu_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }

    private fun showHomeFragment() {
        atHome = true
        bottomNavigation.selectedItemId = R.id.navigation_home
        replaceFragment(R.id.contentFrame, HomeFragment.newInstance(), R.string.fragment_title_home)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean("atHome", atHome)
    }

    override fun onBackPressed() {
        if (!atHome) {
            showHomeFragment()
        } else {
            super.onBackPressed()
        }
    }
}
