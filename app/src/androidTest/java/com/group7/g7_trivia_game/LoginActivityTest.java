package com.group7.g7_trivia_game;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.Intents.times;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

import android.app.Instrumentation;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Intent tests for CreateAccountActivity and MainActivity started by the buttons in LoginActivity
 *
 * @author Rawley Lizarraga
 * @since 8/11/2025
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    //Deprecated but still works
    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void createAccountButton() {
        // Stubs the intent. Not sure if needed here but the android dev tutorial has it and it doesn't break anything so here it is.
        intending(hasComponent(CreateAccountActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(0, null));

        // Simulate pressing the create account button to start intent.
        onView(withId(R.id.loginCreateAccountButton)).perform(click());

        // Check that it created the correct intent. (CreateAccountActivity)
        intended(hasComponent(CreateAccountActivity.class.getName()));
    }

    @Test
    public void loginButton() {
        // Stubs the intent. Not sure if needed here but the android dev tutorial has it and it doesn't break anything so here it is.
        intending(hasComponent(MainActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(0, null));

        onView(withId(R.id.loginUsernameEditText)).perform(typeText("testuser1"), closeSoftKeyboard());
        onView(withId(R.id.loginPasswordEditText)).perform(typeText("testuser1"), closeSoftKeyboard());

        // Simulate pressing the create account button to start intent.
        onView(withId(R.id.loginLoginButton)).perform(click());

        // Check that it created the correct intent (CreateAccountActivity) with the test user's id
        int testUser1Id = 2;
        intended(allOf(hasComponent(MainActivity.class.getName()), hasExtra(MainActivity.MAIN_ACTIVITY_USER_ID, testUser1Id)));
    }
}