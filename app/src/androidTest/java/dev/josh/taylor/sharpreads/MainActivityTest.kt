package dev.josh.taylor.sharpreads

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityTestRule(MainActivity::class.java)

    private val activity: MainActivity
        get() = activityScenarioRule.activity

    @Test
    fun bookListWasShownAtLaunch() {
        // when - the activity starts
        // then
        onView(withText("Brave New World")).check(matches(isDisplayed()))
    }

    @Test
    fun bookDetailAppeared() {
        // when
        onView(withId(R.id.bookList))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // then
        onView(withContentDescription(R.string.book_title_desc))
            .check(matches(withText("Brave New World")))
    }

    @Test
    fun bookDetailReturnedToBookList() {
        // given
        onView(withId(R.id.bookList))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // when
        Espresso.pressBack()
        // then
        onView(withText("Brave New World")).check(matches(isDisplayed()))
    }

    @Test
    fun signInFlowAppeared() {
        // when
        onView(withId(R.id.accountMenuItem)).perform(click())
        // then
        onView(withId(R.id.signInMessage)).check(matches(withText(R.string.sign_in_message)))
    }

    @Test
    fun signInFlowClosed() {
        // given
        onView(withId(R.id.accountMenuItem)).perform(click())
        // when
        Espresso.pressBack()
        // then
        onView(withText("Brave New World")).check(matches(isDisplayed()))
    }
}