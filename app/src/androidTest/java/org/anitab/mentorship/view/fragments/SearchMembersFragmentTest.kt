package org.anitab.mentorship.view.fragments

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.anitab.mentorship.R
import org.anitab.mentorship.view.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchMembersFragmentTest {

    // Start the MainActivity
    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    /*
    * This test tests that the MembersFragment() is loading correctly.
    * */
    @Test
    fun launch_SearchMembersFragment() {
        activityTestRule.runOnUiThread {
            activityTestRule.activity.replaceFragment(
                R.id.container,
                MembersFragment.newInstance(),
                R.string.fragment_title_members
            )

            // clicking on search icon
            Espresso.onView(withId(R.id.search_item)).perform(click())

            // typing test username to search
            val username = "joserio"
            Espresso.onView(withId(R.id.svSearchMembers)).perform(typeText(username))

            // checking if searched username is displayed
            Espresso.onView(withId(R.id.rvSearchedMembers)).check(matches(withText(username)))
        }
    }
}
