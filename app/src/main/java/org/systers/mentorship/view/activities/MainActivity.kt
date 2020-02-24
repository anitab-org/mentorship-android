package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.R
import org.systers.mentorship.db.AppDatabase
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.*


/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
const val START_FRAGMENT = "start_fragment"
const val FRAGMENT_RELATION = "fragment_relation"
const val FRAGMENT_REQUESTS = "fragment_requests"

class MainActivity : BaseActivity() {

    private var atHome = true

    private val preferenceManager: PreferenceManager = PreferenceManager()
    private val db: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            showHomeFragment()
        } else {
            atHome = savedInstanceState.getBoolean("atHome")
        }

        val startFragment = intent?.extras?.getString(START_FRAGMENT)
        when (startFragment) {
            FRAGMENT_RELATION -> showRelationFragment()
            FRAGMENT_REQUESTS -> showRequestsFragment()
            else -> {
            }
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(
                        R.id.contentFrame, HomeFragment.newInstance(), R.string.fragment_title_home
                    )
                    atHome = true
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    replaceFragment(
                        R.id.contentFrame,
                        ProfileFragment.newInstance(),
                        R.string.fragment_title_profile
                    )
                    atHome = false
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_relation -> {
                    replaceFragment(
                        R.id.contentFrame,
                        RelationPagerFragment.newInstance(),
                        R.string.fragment_title_relation
                    )
                    atHome = false
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_members -> {
                    replaceFragment(
                        R.id.contentFrame,
                        MembersFragment.newInstance(),
                        R.string.fragment_title_members
                    )
                    atHome = false
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_requests -> {
                    replaceFragment(
                        R.id.contentFrame,
                        RequestsFragment.newInstance(),
                        R.string.fragment_title_requests
                    )
                    atHome = false
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.menu_notifications)
        MenuItemCompat.setActionView(item, R.layout.notification_count_badge)
        val layout = MenuItemCompat.getActionView(item) as ConstraintLayout
        val button = layout.findViewById(R.id.menu_notifications_button) as ImageButton
        val tv = layout.findViewById(R.id.actionbar_notifcation_textview) as TextView

        fun onClick() = startActivity(Intent(this, NotificationsActivity::class.java))

        button.setOnClickListener { onClick() }
        tv.setOnClickListener { onClick() }

        db.notificationDao().findAll().observe(this, Observer {
            tv.text = it.size.toString()
            tv.visibility = if(it.isNullOrEmpty()) {
                View.INVISIBLE
            } else View.VISIBLE
        })


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_settings -> {
            startActivity(Intent(this, SettingsActivity::class.java))
            true
        }
        R.id.menu_notifications -> {
            startActivity(Intent(this, NotificationsActivity::class.java))
            true
        }
        else -> false
    }

    private fun showHomeFragment() {
        atHome = true
        bottomNavigation.selectedItemId = R.id.navigation_home
        replaceFragment(R.id.contentFrame, HomeFragment.newInstance(), R.string.fragment_title_home)
    }

    private fun showRelationFragment() {
        atHome = false
        bottomNavigation.selectedItemId = R.id.navigation_relation
        replaceFragment(R.id.contentFrame, RelationPagerFragment.newInstance(), R.string.fragment_title_relation)
    }

    private fun showRequestsFragment() {
        atHome = false
        bottomNavigation.selectedItemId = R.id.navigation_requests
        replaceFragment(R.id.contentFrame, RequestsFragment.newInstance(), R.string.fragment_title_requests)
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
