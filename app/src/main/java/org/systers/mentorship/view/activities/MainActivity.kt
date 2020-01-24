package org.systers.mentorship.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.adapters.MainPagerAdapter
import org.systers.mentorship.view.fragments.*

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity: BaseActivity(), ViewPager.OnPageChangeListener {

    /**
     * This class represents the index of each tab in the Bottom Navigation View
     * */
    enum class BottomNavigationIndex(val value: Int){
        HOME(0),
        PROFILE(1),
        RELATION(2),
        MEMBERS(3),
        REQUESTS(4)
    }

    private var atHome = true

    private val preferenceManager: PreferenceManager = PreferenceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavigation()

        bottomNavigation.setOnClickMenuListener {item ->
            vpBottomNavigation.currentItem = item.id
            atHome = item.id == BottomNavigationIndex.HOME.value
        }

        vpBottomNavigation.adapter = MainPagerAdapter(supportFragmentManager)
        vpBottomNavigation.addOnPageChangeListener(this)

        if (savedInstanceState == null) {
            showHomeFragment()
        } else {
            atHome = savedInstanceState.getBoolean("atHome")
        }

    }

    private fun initBottomNavigation() {
        bottomNavigation.add(MeowBottomNavigation.Model(BottomNavigationIndex.HOME.value, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(BottomNavigationIndex.PROFILE.value, R.drawable.ic_profile_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(BottomNavigationIndex.RELATION.value, R.drawable.ic_current_relation_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(BottomNavigationIndex.MEMBERS.value, R.drawable.ic_members_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(BottomNavigationIndex.REQUESTS.value, R.drawable.ic_requests_black_24dp))
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
        bottomNavigation.show(BottomNavigationIndex.HOME.value)
        bottomNavigation.id = R.id.navigation_home
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
            0 -> bottomNavigation.id = R.id.navigation_home

            1 -> bottomNavigation.id = R.id.navigation_profile

            2 -> bottomNavigation.id = R.id.navigation_relation

            3 -> bottomNavigation.id = R.id.navigation_members

            4 -> bottomNavigation.id = R.id.navigation_requests
        }
        bottomNavigation.show(position)
    }
}
