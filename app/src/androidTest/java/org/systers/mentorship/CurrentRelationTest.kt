package org.systers.mentorship

import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.systers.mentorship.view.activities.LoginActivity

@RunWith(AndroidJUnit4::class)
class CurrentRelationTest {

    private val CORRECT_TEST_USERNAME = "ursula"
    private val CORRECT_TEST_PASSWORD = "12345678"

    @get:Rule
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    private fun findEditTextInTextInputLayout(@IdRes textInputLayoutId: Int): ViewInteraction {

        return onView(CoreMatchers.allOf(isDescendantOfA(withId(textInputLayoutId)), isAssignableFrom(EditText::class.java)))
    }

    /**
     * Before the tests can be performed the application context should be at Relation Fragment.
     * In order to achieve this pre-requisite @Before is used below which will run before any test is executed.
     */
    @Before
    fun testLoginButtonClickedWhenDataIsCorrect() {
        enterCredentials(CORRECT_TEST_USERNAME, CORRECT_TEST_PASSWORD)
        Thread.sleep(3000)
        onView(withId(R.id.navigation_relation)).perform(click())
        Thread.sleep(3000)
    }

    /**
     * This test checks if the no tasks and no achievements messages are shown
     * correctly.
     */

    @Test
    fun testIfEmptyTaskAndEmptyAchievementShowCorrectMessage() {
        onView(withText("TASKS")).perform(click())
        onView(withText(R.string.no_tasks)).check(matches(withText("No tasks")))
        onView(withText(R.string.no_achievements)).check(matches(withText("No achievements")))
    }

    /**
     * This test checks if the Relations tab is selected
     * by the user.
     */
    @Test
    fun checkIfDetailsTabContainsRelationDetails() {
        onView(withId(R.id.tvMenteeLabel)).check(matches(withText("Mentee:")))
        onView(withId(R.id.tvMentorLabel)).check(matches(withText("Mentor:")))
        onView(withId(R.id.tvEndDateLabel)).check(matches(withText("End date:")))
        onView(withId(R.id.tvNotesLabel)).check(matches(withText("Notes:")))
        onView(withId(R.id.btnCancelRelation)).check(matches(withText("Cancel")))
    }

    /**
     * This test checks if a task is successfully created
     * by a user.
     */

    @Test
    fun testCreateTask() {
        onView(withText("TASKS")).perform(click())
        onView(withId(R.id.tlMentorshipRelation)).perform(swipeUp())
        onView(withId(R.id.fabAddItem)).perform(click())
        onView(withId(R.id.etAddTask)).perform(typeText("Hello"))
        onView(withText("SAVE")).perform(click())
        Thread.sleep(1000)
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText("Task created")))
        Thread.sleep(3000)
    }

    /**
     * This test checks if the completed task can be marked as done
     * by the user.
     */

    @Test
    fun testMarkTaskCompleted() {
        onView(withText("TASKS")).perform(click())
        onView(withId(R.id.rvTasks)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, clickItemWithId(R.id.cbTask)))
        onView(withText("Yes")).perform(click())
        Thread.sleep(2000)
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText("Task marked as complete")))
        Thread.sleep(3000)
    }

    fun clickItemWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on task hello"
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id) as View
                v.performClick()
            }
        }
    }

    /**
     * This test checks if the confirmation box pops up when user
     * clicks on cancel relation button.
     */
    @Test
    fun testConfirmationBoxWhenClickedOnCancelRelation() {
        onView(withId(R.id.tlMentorshipRelation)).perform(swipeUp())
        onView(withId(R.id.btnCancelRelation)).perform(click())
        onView(withText(R.string.cancel_relation_text)).check(matches(isDisplayed()))
    }

    private fun enterCredentials(username: String, password: String) {
        // Type in a username
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText(username), closeSoftKeyboard())

        // Type in a password
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText(password), closeSoftKeyboard())

        // Perform a click on the Login Button
        onView(withId(R.id.btnLogin)).perform(click())
    }
}