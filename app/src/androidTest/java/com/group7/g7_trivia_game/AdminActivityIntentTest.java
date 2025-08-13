package com.group7.g7_trivia_game;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

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
 * Tests the intent of the AdminActivity.
 * This test checks if clicking the "Create Question" button
 * launches the CreateQuestionActivity.
 * @author Madison Nolen
 * @since 8/12/2025
 */
@RunWith(AndroidJUnit4.class)
public class AdminActivityIntentTest {

    @Rule
    public ActivityScenarioRule<AdminActivity> activityRule =
            new ActivityScenarioRule<>(new Intent(
                    ApplicationProvider.getApplicationContext(),
                    AdminActivity.class
            ).putExtra("com.group7.g7_trivia_game.MAIN_ACTIVITY_USER_ID", 123)); // fake admin ID

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }


    @Test
    public void clickingCreateQuestionButton_launchesQuestionActivity() {
        onView(withId(R.id.createQuestionButton)).perform(click());
        intended(hasComponent(CreateQuestionActivity.class.getName()));
    }


}
