package com.assignment.myandroidjournal.view.activity;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.assignment.myandroidjournal.R;

import org.junit.Rule;
import org.junit.Test;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

@HiltAndroidTest
public class AddJournalActivityTest {

    @Rule(order = 0)
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Rule(order = 1)
    public ActivityScenarioRule<AddJournalActivity> activityScenarioRule = new ActivityScenarioRule<>(AddJournalActivity.class);


    @Test
    public void AddNewJournalButtonClick()
    {
        hiltRule.inject();

        Espresso.onView(ViewMatchers.withId(R.id.red)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.description)).perform(replaceText("UI TEST..."));
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(click());

    }


}