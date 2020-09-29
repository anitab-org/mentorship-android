package org.systers.mentorship

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.systers.mentorship.view.activities.SendRequestActivity


@RunWith(AndroidJUnit4::class)
class SendRequestTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<SendRequestActivity>(SendRequestActivity::class.java)

    @Test
    //when the radio button and notes are filled
    fun testSendRequestBtnWhenEverythingFilled()
    {
        onView(withId(R.id.btnMentorRadio)).perform(click())
        onView(withId(R.id.etRequestNotes)).perform(typeText("notes"), closeSoftKeyboard())

        onView(withId(R.id.btnSendRequest)).perform(click())

        assertTrue(activityTestRule.activity.isFinishing)
    }

    @Test
    //when radio button is chosen but notes empty
    fun testErrorMsgWhenNotesAreEmpty()
    {
        onView(withId(R.id.btnMenteeRadio)).perform(click())

        onView(withId(R.id.btnSendRequest)).perform(click())

        onView(withId(R.id.etRequestNotes)).check(matches(hasErrorText(
                "Notes cannot be empty")))
    }

    @Test
    //when radio btn is not chosen but notes are filled
    fun testErrorMsgWhenOnlyNotesAreFilled()
    {
        onView(withId(R.id.etRequestNotes)).perform(typeText("notes"), closeSoftKeyboard())
         
        onView(withId(R.id.btnSendRequest)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(
                withText("Please select your role for mentorship relation")))
    }


    @Test
    //when both the radio button and notes are empty
    fun testErrorMsgWhenNothingIsChosen()
    {
        onView(withId(R.id.btnSendRequest)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(
                withText("Please select your role for mentorship relation")))
    }

}
