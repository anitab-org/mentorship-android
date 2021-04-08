package org.systers.mentorship.view.activities

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.systers.mentorship.R

@RunWith(AndroidJUnit4::class)
class AboutActivityTest {

    private lateinit var context: Context

    @Rule
    @JvmField
    var intentsRule = IntentsTestRule(AboutActivity::class.java)

    @Before
    fun initContext() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testGithubButton() {
        val expectedIntent: Matcher<Intent> = allOf(
                hasData(context.getString(R.string.url_github)),
                hasAction(Intent.ACTION_VIEW)
        )
        onView(withId(R.id.btnGit)).perform(scrollTo()).perform(click())
        intended(expectedIntent)
    }
    @Test
    fun testZulipButton(){
        val expectedIntent: Matcher<Intent> = allOf(
                hasData(context.getString(R.string.url_zulip)),
                hasAction(Intent.ACTION_VIEW)
        )
        onView(withId(R.id.btnSlack)).perform(scrollTo()).perform(click())
        intended(expectedIntent)
    }
    @Test
    fun testWebsiteButton(){
        val expectedIntent: Matcher<Intent> = allOf(
                hasData(context.getString(R.string.url_website)),
                hasAction(Intent.ACTION_VIEW)
        )
        onView(withId(R.id.btnWebsite)).perform(scrollTo()).perform(click())
        intended(expectedIntent)
    }
    @Test
    fun testTermsAndConditionsButton(){
        val expectedIntent: Matcher<Intent> = allOf(
                hasData(context.getString(R.string.url_terms)),
                hasAction(Intent.ACTION_VIEW)
        )
        onView(withId(R.id.btnTermsCondition)).perform(scrollTo()).perform(click())
        intended(expectedIntent)
    }
    @Test
    fun testPrivacyButton(){
        val expectedIntent: Matcher<Intent> = allOf(
                hasData(context.getString(R.string.url_privacy)),
                hasAction(Intent.ACTION_VIEW)
        )
        onView(withId(R.id.btnprivacypolicy)).perform(scrollTo()).perform(click())
        intended(expectedIntent)
    }
    @Test
    fun testCodeOfConductButton(){
        val expectedIntent: Matcher<Intent> = allOf(
                hasData(context.getString(R.string.url_code_of_conduct)),
                hasAction(Intent.ACTION_VIEW)
        )
        onView(withId(R.id.btncodeofconduct)).perform(scrollTo()).perform(click())
        intended(expectedIntent)
    }


}
