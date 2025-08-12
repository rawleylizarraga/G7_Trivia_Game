package com.group7.g7_trivia_game;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.app.Instrumentation;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Intent tests for LoginActivity and MainActivity started by the buttons in CreateAccountActivity
 *
 * @author Rawley Lizarraga
 * @since 8/12/2025
 */
@RunWith(AndroidJUnit4.class)
public class CreateAccountActivityTest {

    //Deprecated but still works
    @Rule
    public IntentsTestRule<CreateAccountActivity> intentsTestRule = new IntentsTestRule<>(CreateAccountActivity.class);


    @Test
    public void returnToLoginButton() {
        // Stubs the intent. Not sure if needed here but the android dev tutorial has it and it doesn't break anything so here it is.
        intending(hasComponent(LoginActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(0, null));

        // Simulate pressing the back button to start intent.
        onView(withId(R.id.createAccountBackButton)).perform(click());

        // Check that it created the correct intent. (LoginActivity)
        intended(hasComponent(LoginActivity.class.getName()));
    }

}