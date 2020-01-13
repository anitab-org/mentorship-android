package org.systers.mentorship.view.activities

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.*
import org.systers.mentorship.viewmodels.RequestsViewModel
import kotlin.concurrent.fixedRateTimer

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity: BaseActivity() {

    private var atHome = true
    private lateinit var textNotificationCount: TextView

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private lateinit var requestsList: MutableList<Relationship>
    private var notificationList = preferenceManager.getNotificationList()
    private var notificationFragment = NotificationFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            showHomeFragment()
        } else {
            atHome = savedInstanceState.getBoolean("atHome")
        }
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
        menuInflater.inflate(R.menu.menu_notifications, menu)
        val notificationMenuItem = menu.findItem(R.id.menu_notifications)
        val actionView = notificationMenuItem.actionView
        textNotificationCount= actionView.findViewById(R.id.notification_badge)

        //listener to get request list
        val requestsViewModel = ViewModelProviders.of(this).get(RequestsViewModel::class.java)
        requestsViewModel.successful.observe(this, Observer {
            successful ->
            if (successful != null){
                if (successful) {
                    requestsList = preferenceManager.getRequestsList()
                    val newRequestsList = requestsViewModel.allRequestsList

                    // notificationList = newRequestsList - requestsList
                    val diff = newRequestsList.toSet().minus(requestsList.toSet())
                    /** Show notification when either of following is true
                     * 1. sentByMe is true, request is accepted(state 2)/rejected(state 3)/cancelled(state 4) by other user
                     * 2. sentByMe is false, request is pending(state 1)
                     * 3. request is complete
                     */
                    val newNotifications = diff.filter {
                        (it.sentByMe && (it.state == 2 || it.state == 3 || it.state == 4)) ||
                                (!it.sentByMe && it.state == 1) ||
                                it.state == 5
                    }
                    notificationList.addAll(newNotifications)
                    // clear notificationList when delete button pressed in NotificationFragment
                    if (notificationFragment.clearNotifications){
                        notificationList.clear()
                    }
                    //save notifications and list to shared preferences
                    preferenceManager.putNotifications(notificationList)
                    preferenceManager.putRequests(newRequestsList)
                    setupBadge()
                }
            }
        })
        // poll server for notifications
        fixedRateTimer("timer",false,0,5000){
            this@MainActivity.runOnUiThread {
                requestsViewModel.getAllMentorshipRelations()
            }
        }
        actionView.setOnClickListener {
            onOptionsItemSelected(notificationMenuItem)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.menu_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                R.id.menu_notifications -> {
                    // open notifications box
                    replaceFragment(
                            R.id.contentFrame, notificationFragment,
                            R.string.fragment_title_requests)
                    atHome = false
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

    private fun setupBadge() {
        // find notification count
        if (notificationList.size == 0) {

            if (textNotificationCount.visibility != View.GONE) {
                textNotificationCount.visibility = View.GONE
            }
        } else {
            textNotificationCount.visibility = View.VISIBLE
            textNotificationCount.text = notificationList.size.toString()
            sendNotification()
        }
    }

    fun sendNotification() {
        val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        var mBuilder = NotificationCompat.Builder(MentorshipApplication.getContext(),"channel")
                .setSmallIcon(R.drawable.mentorship_system_logo)
                .setContentTitle("Mentorship notification")

        var counter = 0
        for (message in generateMessages(notificationList)){
            mBuilder.setContentText(message)
            notificationManager?.notify(counter++, mBuilder.build())
        }
    }

    private fun generateMessages(notificationList: List<Relationship>):List<String>{
        val messagesList = mutableListOf<String>()
        for (relationship in notificationList){
            when(relationship.state){
                1 -> messagesList.add(
                        this.getString(R.string.notification_new_request,
                                findSender(relationship).name,
                                findSenderRole(relationship)))
                2 -> messagesList.add(
                        this.getString(R.string.notification_accepted,
                                findSender(relationship).name))
                3 -> messagesList.add(
                        this.getString(R.string.notification_rejected,
                                findSender(relationship).name))
                4 -> messagesList.add(
                        this.getString(R.string.notification_cancelled,
                                findSender(relationship).name,
                                findSenderRole(relationship)))
                5 -> messagesList.add(
                        this.getString(R.string.notification_completed,
                                findSender(relationship).name,
                                findSenderRole(relationship)))
            }
        }
        return messagesList
    }
    private fun findSender(relationship: Relationship): Relationship.RelationUserResponse{
        // helper function to find sender
        if(relationship.actionUserId == relationship.mentee.id){
            return relationship.mentee
        }
        return relationship.mentor
    }
    private fun findSenderRole(relationship: Relationship): String{
        // helper function to find sender
        if(relationship.actionUserId == relationship.mentee.id){
            return this.getString(R.string.mentee)
        }
        return this.getString(R.string.mentor)
    }
}
