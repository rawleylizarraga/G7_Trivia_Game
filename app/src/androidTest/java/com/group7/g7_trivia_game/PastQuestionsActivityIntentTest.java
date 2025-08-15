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
 *  Intent tests for PastQuestionsActivity
 *
 * @author
 * @since 8/13/2025
 */
@RunWith(AndroidJUnit4.class)
public class PastQuestionsActivityIntentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(new Intent(
                    ApplicationProvider.getApplicationContext(),
                    MainActivity.class
            ).putExtra("com.group7.g7_trivia_game.MAIN_ACTIVITY_USER_ID", 123));

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void backButton_launchesPastQuestionsActivity(){
        onView(withId(R.id.pastQuestionsButton)).perform(click());
        intended(hasComponent(PastQuestionsActivity.class.getName()));
    }

}