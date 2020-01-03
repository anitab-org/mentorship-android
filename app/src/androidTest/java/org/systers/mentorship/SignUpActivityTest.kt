package org.systers.mentorship

import androidx.annotation.IdRes
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.widget.EditText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.Constants.MIN_AGE
import org.systers.mentorship.view.activities.SignUpActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * This class specifies the UI test for SignUpActivity
 */
@RunWith(AndroidJUnit4::class)
class SignUpActivityTest {

    private val EMPTY_USERNAME_ERROR: String = "Username cannot be empty"
    private val EMPTY_PASSWORD_ERROR: String = "Password cannot be empty"
    private val EMPTY_EMAIL_ERROR: String = "Email cannot be empty"
    private val EMPTY_NAME_ERROR: String = "Name cannot be empty"

    /**
     * This basically setups the SignUpActivity before test
      */
    @get:Rule
    var mActivityRule: ActivityTestRule<SignUpActivity> = ActivityTestRule(SignUpActivity::class.java)

    /**
     * This method is used to find the EditText within the TextInputLayout. Useful for typing into the TextInputLayout
     */
    fun findEditTextInTextInputLayout(@IdRes textInputLayoutId: Int): ViewInteraction {

        return Espresso.onView(Matchers.allOf(ViewMatchers.isDescendantOfA(ViewMatchers.withId(textInputLayoutId)),ViewMatchers.isAssignableFrom(EditText::class.java)))
    }


    companion object {

        /**
         * This method simply implements the null check, checks the types and casts
         */
        fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> {

            return object: TypeSafeMatcher<View>() {
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


                    val error: CharSequence? = item.error
                    if (error == null) {
                        return false
                    }

                    var errorMsg: String = error.toString()
                    return expectedErrorText.equals(errorMsg)

                }

            }

        }

    }

    /**
     * This test method is defined to test that when all the fields are empty and the user
     * clicks the signUp button then the errors in all the fields are displayed or not
     */
    @Test
    fun testSignUpClickedWhenAllFieldsAreEmpty() {

        findEditTextInTextInputLayout(R.id.tiName).perform(typeText(""), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText(""), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiEmail).perform(typeText(""), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText(""), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiConfirmPassword).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.cbTC)).perform(click())

        onView(withId(R.id.btnSignUp)).perform(scrollTo(),click())

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

        findEditTextInTextInputLayout(R.id.tiName).perform(typeText("name"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText("username"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiEmail).perform(typeText("email@gmail.com"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText("qwertzui"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiConfirmPassword).perform(typeText("12345678"), closeSoftKeyboard())
        onView(withId(R.id.cbTC)).perform(click())

        onView(withId(R.id.btnSignUp)).perform(scrollTo(),click())

        onView(withId(R.id.tiConfirmPassword)).check(matches(hasTextInputLayoutErrorText("Passwords didn't match!")))
    }

    /**
     * This test method simply checks that if the date of birth is empty
     * and user clicks on signUp button then the error message is coming in date of birth editText
     */
    @Test
    fun whenBirthdayIsNotEnteredErrorTest() {
        findEditTextInTextInputLayout(R.id.tiName).perform(typeText("name"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText("username"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiEmail).perform(typeText("email@gmail.com"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText("qwertzui"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiConfirmPassword).perform(typeText("qwertzui"), closeSoftKeyboard())
        onView(withId(R.id.cbTC)).perform(click())

        onView(withId(R.id.btnSignUp)).perform(scrollTo(),click())

        onView(withId(R.id.tiDateOfBirth)).check(matches(hasTextInputLayoutErrorText("Enter the whole date!")))
    }

    /**
     * This test method simply checks that if the date of birth is incorrect
     * and user clicks on signUp button then the error message is coming in date of birth editText
     */
    @Test
    fun whenIncorrectBirthdayIsEnteredErrorTest() {
        findEditTextInTextInputLayout(R.id.tiName).perform(typeText("name"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText("username"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiEmail).perform(typeText("email@gmail.com"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiDateOfBirth).perform(typeText("99999999"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText("qwertzui"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiConfirmPassword).perform(typeText("qwertzui"), closeSoftKeyboard())
        onView(withId(R.id.cbTC)).perform(click())

        onView(withId(R.id.btnSignUp)).perform(scrollTo(),click())

        onView(withId(R.id.tiDateOfBirth)).check(matches(hasTextInputLayoutErrorText("You entered incorrect date!")))
    }

    /**
     * This test method simply checks that if the user hasn't reached min age
     * and user clicks on signUp button then the error message is coming in date of birth editText
     */
    @Test
    fun whenMinAgeIsNotReachedErrorTest() {
        val sdf = SimpleDateFormat(Constants.DATE_FORMAT.replace("/", ""), Locale.getDefault())

        findEditTextInTextInputLayout(R.id.tiName).perform(typeText("name"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText("username"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiEmail).perform(typeText("email@gmail.com"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiDateOfBirth).perform(typeText(sdf.format(System.currentTimeMillis())), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText("qwertzui"), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiConfirmPassword).perform(typeText("qwertzui"), closeSoftKeyboard())
        onView(withId(R.id.cbTC)).perform(click())

        onView(withId(R.id.btnSignUp)).perform(scrollTo(),click())

        onView(withId(R.id.tiDateOfBirth)).check(matches(hasTextInputLayoutErrorText("You must be at least ${-MIN_AGE} years old!")))
    }

}
