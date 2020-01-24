package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.adapters.MainPagerAdapter
import org.systers.mentorship.view.fragments.*

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity: BaseActivity(), ViewPager.OnPageChangeListener {

    private var atHome = true

    private val preferenceManager: PreferenceManager = PreferenceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        bottomNavigationVP.adapter = MainPagerAdapter(supportFragmentManager)
        bottomNavigationVP.addOnPageChangeListener(this)

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
                bottomNavigationVP.currentItem = 0
                atHome = true
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                bottomNavigationVP.currentItem = 1
                atHome = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_relation -> {
                bottomNavigationVP.currentItem = 2
                atHome = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_members -> {
                bottomNavigationVP.currentItem = 3
                atHome = false
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_requests -> {
                bottomNavigationVP.currentItem = 4
                atHome = false
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
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }

    private fun showHomeFragment() {
        atHome = true
        bottomNavigation.selectedItemId = R.id.navigation_home
        bottomNavigationVP.currentItem = 0
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

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> bottomNavigation.selectedItemId = R.id.navigation_home

            1 -> bottomNavigation.selectedItemId = R.id.navigation_profile

            2 -> bottomNavigation.selectedItemId = R.id.navigation_relation

            3 -> bottomNavigation.selectedItemId = R.id.navigation_members

            4 -> bottomNavigation.selectedItemId = R.id.navigation_requests
        }
    }
}
