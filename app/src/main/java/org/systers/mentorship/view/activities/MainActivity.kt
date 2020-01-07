package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.*

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity: BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var atHome = true

    private val preferenceManager: PreferenceManager = PreferenceManager()

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = bottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            showHomeFragment()
        } else {
            atHome = savedInstanceState.getBoolean("atHome")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(R.id.contentFrame, HomeFragment.newInstance(),
                    R.string.fragment_title_home)
                atHome = true
                return true
            }
            R.id.navigation_profile -> {
                replaceFragment(R.id.contentFrame, ProfileFragment.newInstance(),
                    R.string.fragment_title_profile)
                atHome = false
                return true
            }
            R.id.navigation_relation -> {
                replaceFragment(R.id.contentFrame, RelationPagerFragment.newInstance(),
                    R.string.fragment_title_relation)
                atHome = false
                return true
            }
            R.id.navigation_members -> {
                replaceFragment(R.id.contentFrame, MembersFragment.newInstance(),
                    R.string.fragment_title_members)
                atHome = false
                return true
            }
            R.id.navigation_requests -> {
                replaceFragment(R.id.contentFrame, RequestsFragment.newInstance(),
                    R.string.fragment_title_requests)
                atHome = false
                return true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.menu_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }

    fun showHomeFragment() {
        atHome = true
        bottomNavigation.selectedItemId = R.id.navigation_home
        replaceFragment(R.id.contentFrame, HomeFragment.newInstance(), R.string.fragment_title_home)
    }

    fun showProfileFragment() {
        atHome = false
        bottomNavigation.selectedItemId = R.id.navigation_profile
        replaceFragment(R.id.contentFrame, ProfileFragment.newInstance(), R.string.fragment_title_profile)
    }

    fun showRelationFragment() {
        atHome = false
        bottomNavigation.selectedItemId = R.id.navigation_relation
        replaceFragment(R.id.contentFrame, RelationPagerFragment.newInstance(), R.string.fragment_title_relation)
    }

    fun showMembersFragment() {
        atHome = false
        bottomNavigation.selectedItemId = R.id.navigation_members
        replaceFragment(R.id.contentFrame, MembersFragment.newInstance(), R.string.fragment_title_members)
    }

    fun showRequestsFragment() {
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
