package org.systers.mentorship

import android.support.annotation.IdRes
import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.EditText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.systers.mentorship.view.activities.LoginActivity

/**
 * This class specifies the UI test for LoginActivity
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    private val EMPTY_USERNAME_ERROR: String = "Username cannot be empty"
    private val EMPTY_PASSWORD_ERROR: String = "Password cannot be empty"

    private val TEST_USERNAME: String = "test user"
    private val TEST_PASSWORD: String = "test password"

    @get:Rule
    var mActivityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    private fun findEditTextInTextInputLayout(@IdRes textInputLayoutId : Int) : ViewInteraction {

        return onView(allOf(isDescendantOfA(withId(textInputLayoutId)), isAssignableFrom(EditText::class.java)))
    }

    companion object {

        /**
         * This simply implements the null check, checks the type and then casts.
         */
        fun hasTextInputLayoutErrorText(expectedErrorText : String) : Matcher<View> {

            return object : TypeSafeMatcher<View>() {
                /**
                 * Generates a description of the object.  The description may be part of a
                 * a description of a larger object of which this is just a component, so it
                 * should be worded appropriately.
                 *
                 * @param description
                 * The description to be built or appended to.
                 */
                override fun describeTo(description: Description?) {
                    //("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                /**
                 * Subclasses should implement this. The item will already have been checked for
                 * the specific type and will never be null.
                 */
                override fun matchesSafely(item: View?): Boolean {

                    if ((item !is TextInputLayout)) {
                        return false
                    }

                    val error = item.error
                    if (error == null) {
                        return false
                    }

                    val errorMsg: String = error.toString()
                    return expectedErrorText.equals(errorMsg)
                }
            }
        }
    }

    /**
     * This test checks that error messages are shown after the Login button is clicked
     *  with empty username and password fields.
     */
    @Test
    fun testLoginButtonClickedWhenUsernameAndPasswordAreEmpty() {

        // Type empty string in both the editTexts
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText(""), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText(""), closeSoftKeyboard())

        // Perform Click operation on Login Button
        onView(withId(R.id.btnLogin)).perform(click())

        // Checks that the error message in both the editTexts appears after button click
        onView(withId(R.id.tiUsername)).check(matches(hasTextInputLayoutErrorText(EMPTY_USERNAME_ERROR)))
        onView(withId(R.id.tiPassword)).check(matches(hasTextInputLayoutErrorText(EMPTY_PASSWORD_ERROR)))

    }

    /**
     * This test checks that error messages are shown after the Login button is clicked
     *  with empty username and a password.
     */
    @Test
    fun testLoginButtonClickedWhenUsernameIsEmptyAndPasswordIsFilled() {
        // Type in no username
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText(""), closeSoftKeyboard())

        // Type in a password
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText(TEST_PASSWORD), closeSoftKeyboard())

        // Perform Click operation on Login Button
        onView(withId(R.id.btnLogin)).perform(click())

        // Check for error message on username and not on password
        onView(withId(R.id.tiUsername)).check(matches(hasTextInputLayoutErrorText(EMPTY_USERNAME_ERROR)))
        onView(withId(R.id.tiPassword)).check(matches(not(hasTextInputLayoutErrorText(EMPTY_PASSWORD_ERROR))))
    }

    /**
     *  This test checks that error messages are shown after the Login button is clicked
     *  with a username and an empty password.
     */
    @Test
    fun testLoginButtonClickedWhenUsernameIsFilledAndPasswordIsEmpty() {
        // Type in a username
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText(TEST_USERNAME), closeSoftKeyboard())

        // Type in no password
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText(""), closeSoftKeyboard())

        // Perform Click operation on Login Button
        onView(withId(R.id.btnLogin)).perform(click())

        // Check for no error message on username with an error message on password
        onView(withId(R.id.tiUsername)).check(matches(not(hasTextInputLayoutErrorText(EMPTY_USERNAME_ERROR))))
        onView(withId(R.id.tiPassword)).check(matches(hasTextInputLayoutErrorText(EMPTY_PASSWORD_ERROR)))
    }
}
