package org.systers.mentorship.view.activities

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants.NUM_PAGES
import org.systers.mentorship.utils.DepthPageTransformer
import org.systers.mentorship.view.fragments.*

/**
 * This activity has the bottom navigation which allows the user to switch between fragments
 */
class MainActivity : BaseActivity() {

    private var atHome = true

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagerMain.adapter = ScreenSlidePagerAdapter(this)
        viewPagerMain.setPageTransformer(DepthPageTransformer())
        // for BottomNavigationBar to represent the new fragment
        viewPagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigation.selectedItemId =
                        when (position) {
                            0 -> R.id.navigation_home
                            1 -> R.id.navigation_profile
                            2 -> R.id.navigation_relation
                            3 -> R.id.navigation_members
                            4 -> R.id.navigation_requests
                            else -> R.id.navigation_home
                        }
                atHome = position == 0
            }
        })
        // in order to allow to use ViewPager inside of a ViewPager
        viewPagerMain.reduceDragSensitivity()

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
                        viewPagerMain.currentItem = 0
                        atHome = true
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_profile -> {
                        viewPagerMain.currentItem = 1
                        atHome = false
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_relation -> {
                        viewPagerMain.currentItem = 2
                        atHome = false
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_members -> {
                        viewPagerMain.currentItem = 3
                        atHome = false
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_requests -> {
                        viewPagerMain.currentItem = 4
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
        viewPagerMain.currentItem = 0
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

    private fun ViewPager2.reduceDragSensitivity() {
        val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        val recyclerView = recyclerViewField.get(this) as RecyclerView

        val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true
        val touchSlop = touchSlopField.get(recyclerView) as Int
        touchSlopField.set(recyclerView, (touchSlop*1.6).toInt())       // "1.6" was obtained experimentally
    }

    /**
     * A simple pager adapter that represents 5 main fragments
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int) =
                when (position) {
                    0 -> HomeFragment.newInstance()
                    1 -> ProfileFragment.newInstance()
                    2 -> RelationPagerFragment.newInstance()
                    3 -> MembersFragment.newInstance()
                    4 -> RequestsFragment.newInstance()
                    else -> HomeFragment.newInstance()
                }
    }

}
