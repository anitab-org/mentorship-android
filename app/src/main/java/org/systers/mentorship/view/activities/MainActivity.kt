package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.fragments.*

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity: BaseActivity() {

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

        pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)

        /**
         * Listener to find current page. This is to highlight the bottom navigation bar when
         * swipes are performed.
         */
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                bottomNavigation.selectedItemId = when(position){
                    0 -> R.id.navigation_home
                    1 -> R.id.navigation_profile
                    2 -> R.id.navigation_relation
                    3 -> R.id.navigation_members
                    4 -> R.id.navigation_requests
                    else -> R.id.navigation_home
                }
            }
        })
    }

    /**
     * A pager adapter that returns various fragments on swipe.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 5
        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> {
                    atHome = true
                    HomeFragment.newInstance()
                }
                1 -> {
                    atHome = false
                    ProfileFragment.newInstance()
                }
                2 -> {
                    atHome = false
                    RelationPagerFragment.newInstance()
                }
                3 -> {
                    atHome = false
                    MembersFragment.newInstance()
                }
                4 -> {
                    atHome = false
                    RequestsFragment.newInstance()
                }
                else -> {
                    atHome = true
                    HomeFragment.newInstance()
                }
            }
        }
    }

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                pager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                pager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_relation -> {
                pager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_members -> {
                pager.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_requests -> {
                pager.currentItem = 4
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
        pager.currentItem = 0
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
