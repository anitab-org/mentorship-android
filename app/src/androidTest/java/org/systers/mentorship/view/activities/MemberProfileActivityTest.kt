package org.systers.mentorship.view.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule

import org.junit.Rule
import org.junit.Test
import org.systers.mentorship.R

/**
 * This class specifies the UI test for MemberProfileActivity
 */
class MemberProfileActivityTest {

    @get:Rule
    val mActivityTestRule = ActivityTestRule<MemberProfileActivity>(MemberProfileActivity::class.java)

    // Just checks if  all the views are enabled
    @Test
    fun testLaunch() {
        onView(withId(R.id.tvName)).check(matches(isEnabled()))
        onView(withId(R.id.tvUsername)).check(matches(isEnabled()))
        onView(withId(R.id.tvSlackUsername)).check(matches(isEnabled()))
        onView(withId(R.id.tvAvailableToMentor)).check(matches(isEnabled()))
        onView(withId(R.id.tvNeedMentoring)).check(matches(isEnabled()))
        onView(withId(R.id.tvInterests)).check(matches(isEnabled()))
        onView(withId(R.id.tvBio)).check(matches(isEnabled()))
        onView(withId(R.id.tvLocation)).check(matches(isEnabled()))
        onView(withId(R.id.tvOccupation)).check(matches(isEnabled()))
        onView(withId(R.id.tvOrganization)).check(matches(isEnabled()))
        onView(withId(R.id.tvSkills)).check(matches(isEnabled()))
        onView(withId(R.id.btnSendRequest)).check(matches(isEnabled()))
    }

}