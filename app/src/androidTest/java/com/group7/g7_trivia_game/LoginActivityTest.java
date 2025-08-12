package com.group7.g7_trivia_game;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.app.Instrumentation;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

/**
 * Intent tests for login activity buttons
 *
 * @author Rawley Lizarraga
 * @since 8/11/2025
 */
public class LoginActivityTest {

    //deprecated but still works
    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void createAccountButton() {
        intending(hasComponent(CreateAccountActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(0, null));

        onView(withId(R.id.loginCreateAccountButton)).perform(click());

        intended(hasComponent(CreateAccountActivity.class.getName()));
    }


}