package org.systers.mentorship

import androidx.annotation.IdRes
import com.google.android.material.textfield.TextInputLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
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

    private val USER_OR_PASSWORD_IS_WRONG = "Username or password is wrong."

    @get:Rule
    var mActivityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    private fun findEditTextInTextInputLayout(@IdRes textInputLayoutId : Int) : ViewInteraction {

        return onView(allOf(isDescendantOfA(withId(textInputLayoutId)), isAssignableFrom(EditText::class.java)))
    }

    private fun hasTextInputLayoutHintText(expectedHint: String) =
            object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description?) {}

                override fun matchesSafely(item: View?): Boolean {
                    if (item !is TextInputLayout)
                        return false

                    val hint = item.hint.toString()

                    return expectedHint == hint
                }

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
     * This test simply checks if all views are enabled and have the corresponding text/hint
     */
    @Test
    fun testAllViewsEnabledAndHaveCorrectTextOrHint() {
        onView(withId(R.id.tiUsername)).check(matches(isEnabled()))
                .check(matches(hasTextInputLayoutHintText(
                        mActivityRule.activity.getString(R.string.username_or_email))))
        onView(withId(R.id.tiPassword)).check(matches(isEnabled()))
                .check(matches(hasTextInputLayoutHintText(
                        mActivityRule.activity.getString(R.string.password))))
        onView(withId(R.id.btnLogin)).check(matches(isEnabled())).check(matches(withText(R.string.login)))
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

    /**
     *  This test checks that the error message that the data is wrong is being showed
     *  in the SnackBar as the user just can't exist because of the whitespaces in password.
     */
    @Test
    fun testLoginButtonClickedWhenAllFieldsAreFilled() {
        // Type in a username and a password
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText(TEST_USERNAME), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText(TEST_PASSWORD), closeSoftKeyboard())

        // Perform Click operation on Login Button
        onView(withId(R.id.btnLogin)).perform(click())

        // Check for the error message in SnackBar, the user does not exist
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(USER_OR_PASSWORD_IS_WRONG)))
    }

}
