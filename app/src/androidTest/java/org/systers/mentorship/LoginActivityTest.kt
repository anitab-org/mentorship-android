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
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.android.synthetic.main.activity_login.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.systers.mentorship.R.id.tiUsername
import org.systers.mentorship.view.activities.LoginActivity

/**
 * This class specifies the UI test for LoginActivity
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    val EMPTY_USERNAME_ERROR: String = "Username cannot be empty"
    val EMPTY_PASSWORD_ERROR: String = "Password cannot be empty"

    @get:Rule
      var mActivityTestRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

     private fun findEditTextInTextInputLayout(@IdRes textInputLayoutId: Int): ViewInteraction {

          return onView(allOf(isDescendantOfA(withId(textInputLayoutId)), isAssignableFrom(EditText::class.java)))
      }

    companion object {

        /**
         * This simply implements the null check, checks the type and then casts.
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

                  var error: CharSequence? = item.error
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
     * This test method tests the case when user clicks the Login Button leaving
     * all the fields empty
     */
    @Test
     fun testLoginButtonClickedOnEmptyStateOfEditTexts() {

        // Type empty string in both the editTexts
        findEditTextInTextInputLayout(R.id.tiUsername).perform(typeText(""), closeSoftKeyboard())
        findEditTextInTextInputLayout(R.id.tiPassword).perform(typeText(""), closeSoftKeyboard())

        // Perform Click operation on Login Button
        onView(withId(R.id.btnLogin)).perform(click())

        // Checks that the error message in both the editTexts appears after button click
        onView(withId(R.id.tiUsername)).check(matches(hasTextInputLayoutErrorText(EMPTY_USERNAME_ERROR)))
        onView(withId(R.id.tiPassword)).check(matches(hasTextInputLayoutErrorText(EMPTY_PASSWORD_ERROR)))

    }
}