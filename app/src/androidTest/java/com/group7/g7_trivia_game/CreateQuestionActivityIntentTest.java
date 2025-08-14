package com.group7.g7_trivia_game;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 *  Intent tests for CreateQuestionActivity
 *  This test checks if clicking the back button launches the AdminActivity
 * @author Marco Lara
 * @since 8/13/2025
 */
@RunWith(AndroidJUnit4.class)
public class CreateQuestionActivityIntentTest {

    @Rule
    public ActivityScenarioRule<CreateQuestionActivity> activityRule =
            new ActivityScenarioRule<>(new Intent(
                    ApplicationProvider.getApplicationContext(),
                    CreateQuestionActivity.class
            ).putExtra("userId", 123));

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

    @Test
    public void backButton_launchesAdminActivity(){
        onView(withId(R.id.createQuestionBackButton)).perform(click());
        intended(hasComponent(AdminActivity.class.getName()));

    }
}