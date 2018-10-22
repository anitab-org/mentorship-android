package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.BottomNavigationViewHelper
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.*

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity : BaseActivity() {

    private val preferenceManager: PreferenceManager = PreferenceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation)

        if (savedInstanceState == null) {
            replaceFragment(R.id.contentFrame, HomeFragment.newInstance(), R.string.fragment_title_home)
        }
    }

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(R.id.contentFrame, HomeFragment.newInstance(),
                        R.string.fragment_title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                replaceFragment(R.id.contentFrame, ProfileFragment.newInstance(),
                        R.string.fragment_title_profile)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_relation -> {
                replaceFragment(R.id.contentFrame, RelationFragment.newInstance(),
                        R.string.fragment_title_relation)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_members -> {
                replaceFragment(R.id.contentFrame, MembersFragment.newInstance(),
                        R.string.fragment_title_members)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_requests -> {
                replaceFragment(R.id.contentFrame, RequestsFragment.newInstance(),
                        R.string.fragment_title_requests)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.menu_settings -> {
                    true
                }
                R.id.menu_about -> {
                    true
                }
                R.id.menu_feedback -> {
                    true
                }
                R.id.menu_logout -> {
                    preferenceManager.clear()
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
}
