package org.systers.mentorship.view.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.systers.mentorship.R
import org.systers.mentorship.utils.AssertFunctions.Companion.findEditTextInTextInputLayout
import org.systers.mentorship.utils.AssertFunctions.Companion.getString
import org.systers.mentorship.utils.AssertFunctions.Companion.hasTextInputLayoutErrorText

/**
 * This class specifies the UI test for SignUpActivity
 */
class SignUpActivityTest {

    private val EMPTY_USERNAME_ERROR: String = getString(R.string.error_empty_username)
    private val EMPTY_PASSWORD_ERROR: String = getString(R.string.error_empty_password)
    private val EMPTY_EMAIL_ERROR: String = getString(R.string.error_empty_email)
    private val EMPTY_NAME_ERROR: String = getString(R.string.error_empty_name)

    /**
     * This basically setups the SignUpActivity before test
     */
    @get:Rule
    var mActivityRule: ActivityTestRule<SignUpActivity> = ActivityTestRule(SignUpActivity::class.java)

    /**
     * This test method is defined to test that when all the fields are empty and the user
     * clicks the signUp button then the errors in all the fields are displayed or not
     */
    @Test
    fun testSignUpClickedWhenAllFieldsAreEmpty() {
        onView(withId(R.id.cbTC)).perform(click())

        onView(withId(R.id.btnSignUp)).perform(scrollTo(), click())

        onView(withId(R.id.tiName)).check(matches(hasTextInputLayoutErrorText(EMPTY_NAME_ERROR)))
        onView(withId(R.id.tiUsername)).check(matches(hasTextInputLayoutErrorText(EMPTY_USERNAME_ERROR)))
        onView(withId(R.id.tiEmail)).check(matches(hasTextInputLayoutErrorText(EMPTY_EMAIL_ERROR)))
        onView(withId(R.id.tiPassword)).check(matches(hasTextInputLayoutErrorText(EMPTY_PASSWORD_ERROR)))
    }

    /**
     * This test method simply checks that if the password and confirm password are unequal
     * and user clicks on signUp button then the error message is coming in confirm
     * password editText
     */
    @Test
    fun whenPassAreUnequalShowsErrorTest() {
        findEditTextInTextInputLayout(R.id.tiName).perform(typeText("name"))
        findEditTextInTextInputLayout(R.id.tiUsername).perform(scrollTo(), typeText("username"))
        findEditTextInTextInputLayout(R.id.tiEmail).perform(scrollTo(), typeText("email@test.com"))
        findEditTextInTextInputLayout(R.id.tiPassword).perform(scrollTo(), typeText("qwertz123!"))
        findEditTextInTextInputLayout(R.id.tiConfirmPassword).perform(scrollTo(),
                typeText("qwertz123!45"), closeSoftKeyboard())
        onView(withId(R.id.cbTC)).perform(click())

        onView(withId(R.id.btnSignUp)).perform(scrollTo(), click())

        onView(withId(R.id.tiConfirmPassword)).check(matches(
                hasTextInputLayoutErrorText("Passwords didn't match!")))
    }

}
