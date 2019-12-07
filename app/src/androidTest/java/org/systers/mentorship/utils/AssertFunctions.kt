package org.systers.mentorship.utils

import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class AssertFunctions {

    companion object {
        /**
         * This method is used to find the EditText within the TextInputLayout. Useful for typing into the TextInputLayout
         */
        fun findEditTextInTextInputLayout(@IdRes textInputLayoutId: Int) =
                Espresso.onView(CoreMatchers.allOf(ViewMatchers.isDescendantOfA(ViewMatchers.withId(textInputLayoutId)), ViewMatchers.isAssignableFrom(EditText::class.java)))

        fun getString(id: Int): String = InstrumentationRegistry.getInstrumentation().targetContext.getString(id)

        /**
         * This method simply implements the null check, checks the types and casts
         */
        fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> {

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

                    val error: CharSequence = item.error ?: return false

                    val errorMsg: String = error.toString()
                    return expectedErrorText == errorMsg
                }
            }
        }
    }

}