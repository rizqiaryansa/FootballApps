package com.aryansa.rizqi.footballapps

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.aryansa.rizqi.footballapps.R.id.*
import com.aryansa.rizqi.footballapps.view.activity.HomeActivity
import org.junit.Rule
import org.junit.Test

class FootballAppTestUI {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)
    private val wait: Long = 3000

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(wait)
        onView(withId(rvPastEvent)).check(matches(isDisplayed()))
        onView(withId(rvPastEvent)).perform(RecyclerViewActions.
                scrollToPosition<RecyclerView.ViewHolder>(6))
        onView(withId(rvPastEvent)).perform(
                RecyclerViewActions.actionOnItemAtPosition<
                        RecyclerView.ViewHolder>(6, click()))
    }

    @Test
    fun testAppBehaviourUI() {
        Thread.sleep(wait)

        onView(withId(container)).check(matches(isDisplayed()))
        onView(withId(navigation_matches)).check(matches(isDisplayed()))
        onView(withId(navigation_teams)).check(matches(isDisplayed()))
        onView(withId(navigation_favorites)).check(matches(isDisplayed()))

        onView(withId(navigation_teams)).perform(click())

        onView(withId(rvListTeams)).check(matches(isDisplayed()))
        onView(withId(rvListTeams))
                .perform(RecyclerViewActions.actionOnItemAtPosition
                <RecyclerView.ViewHolder>(5, click()));

        Thread.sleep(wait)
        onView(withId(lyTeamDetail)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        pressBack()

        Thread.sleep(wait)

        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_favorites)).perform(click())
    }

}