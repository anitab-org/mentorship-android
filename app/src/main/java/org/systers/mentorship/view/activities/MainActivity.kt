package org.systers.mentorship.view.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.*

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity : BaseActivity() {

    private var atHome = true

    private val preferenceManager: PreferenceManager = PreferenceManager()

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
