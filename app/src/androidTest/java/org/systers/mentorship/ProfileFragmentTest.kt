package org.systers.mentorship

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.systers.mentorship.utils.CountingIdlingResourceSingleton
import org.systers.mentorship.view.activities.MainActivity

@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {

    // launch main activity
    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    /*
    * Test to check that all the edit Text fields are present, enabled and not focusable
    * */
    @Test
    fun check_if_all_the_edit_text_fields_are_present_and_are_not_focusable() {
        onView(withId(R.id.navigation_profile)).perform(click())
        onView(withId(R.id.etUserName)).check(matches(not(isEnabled()))).check(matches(isFocusable()))
        onView(withId(R.id.tiUsername)).check(matches(isEnabled())).check(matches(not(isFocusable())))
        onView(withId(R.id.tiEmail)).check(matches(isEnabled())).check(matches(not(isFocusable())))
        onView(withId(R.id.tiBio)).check(matches(isEnabled())).check(matches(not(isFocusable())))
        onView(withId(R.id.tiSlackUsername)).check(matches(isEnabled())).check(matches(not(isFocusable())))
        onView(withId(R.id.tiLocation)).check(matches(isEnabled())).check(matches(not(isFocusable())))
        onView(withId(R.id.tiOccupation)).check(matches(isEnabled())).check(matches(not(isFocusable())))
        onView(withId(R.id.tiOrganization)).check(matches(isEnabled())).check(matches(not(isFocusable())))
        onView(withId(R.id.tiSkills)).check(matches(isEnabled())).check(matches(not(isFocusable())))
        onView(withId(R.id.tiInterests)).check(matches(isEnabled())).check(matches(not(isFocusable())))
    }

    /*
    * Test to check that the email and username fields are filled with data and are not empty
    * */
    @Test
    fun check_if_the_edit_fields_are_filled_with_necessary_data() {
        // launch my profile fragment
        onView(withId(R.id.navigation_profile)).perform(click())

        // check that the fields are not empty
        onView(withId(R.id.tiUsername)).check(matches(isEnabled())).check(matches(not(withText(""))))
        onView(withId(R.id.tiEmail)).check(matches(isEnabled())).check(matches(not(withText(""))))
    }

    /*
    * Test to check that the Edit Profile Button is working correctly and launching the edit profile fragment
    * */
    @Test
    fun launch_edit_profile_fragment() {
        onView(withId(R.id.navigation_profile)).perform(click())
        onView(withId(R.id.menu_edit_profile)).perform(click())
    }
}