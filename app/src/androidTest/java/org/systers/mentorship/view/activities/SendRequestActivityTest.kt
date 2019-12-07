package org.systers.mentorship.view.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertTrue

import org.junit.Rule
import org.junit.Test
import org.systers.mentorship.R
import org.systers.mentorship.utils.AssertFunctions.Companion.hasTextInputLayoutErrorText
import org.systers.mentorship.utils.AssertFunctions.Companion.getString

/**
 * This class specifies the UI test for SendRequestActivity
 */
class SendRequestActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<SendRequestActivity>(SendRequestActivity::class.java)

    @Test
    fun testSendRequestButtonClickedWhenRadioGroupAndNotesAreEmpty() {
        onView(withId(R.id.btnSendRequest)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.error_send_request_relation)))
    }

    @Test
    fun testSendRequestButtonClickedWhenRadioGroupIsMentorAndNotesAreEmpty() {
        onView(withId(R.id.btnMentorRadio)).perform(click())

        onView(withId(R.id.btnSendRequest)).perform(click())

        onView(withId(R.id.etRequestNotes)).check(matches(hasErrorText(
                getString(R.string.notes_empty_error))))
    }

    @Test
    fun testSendRequestButtonClickedWhenRadioGroupIsMenteeAndNotesAreEmpty() {
        onView(withId(R.id.btnMenteeRadio)).perform(click())

        onView(withId(R.id.btnSendRequest)).perform(click())

        onView(withId(R.id.etRequestNotes)).check(matches(hasErrorText(
                getString(R.string.notes_empty_error))))
    }

    //mentee "user" doesn't exist
    @Test
    fun testSendRequestButtonClickedWhenRadioGroupAndNotesAreFilled() {
        onView(withId(R.id.btnMentorRadio)).perform(click())
        onView(withId(R.id.etRequestNotes)).perform(typeText("notes"), closeSoftKeyboard())

        onView(withId(R.id.btnSendRequest)).perform(click())

        assertTrue(activityTestRule.activity.isFinishing)
    }

}