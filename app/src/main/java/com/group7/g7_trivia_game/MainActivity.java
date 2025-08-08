package com.group7.g7_trivia_game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.group7.g7_trivia_game.databinding.ActivityMainBinding;
import com.group7.g7_trivia_game.viewmodels.MainActivityViewModel;

/**
 * Activity that serves as the main entry point for the trivia game application.
 * It handles user login, displays a welcome message,
 * and provides navigation to various game features such as playing trivia,
 * viewing past questions, and checking the leaderboard.
 * @author Madison Nolen
 * @since 8/2/2025
 */
public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTIVITY_USER_ID = "com.group7.g7_trivia_game.MAIN_ACTIVITY_USER_ID";
    private static final String SAVED_INSTANCE_USERID_KEY = "com.group7.g7_trivia_game.SAVED_INSTANCE_USERID_KEY";
    private static final int LOGGED_OUT = -1;

    private int loggedInUserId = LOGGED_OUT;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        loginUser(savedInstanceState); // Step 1: Find the logged-in user

        // User is not logged in at this point, go to login screen
        if (loggedInUserId == -1) {
            Intent intent = IntentFactory.loginActivityIntentFactory(getApplicationContext());
            startActivity(intent);
        }

        setupButtons();

    }


    private void loginUser(Bundle savedInstanceState) {
        //check shared preference for logged in user read from the file
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        loggedInUserId = sharedPreferences.getInt(getString(R.string.preference_userId_key), LOGGED_OUT);

        if (loggedInUserId == LOGGED_OUT && savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_INSTANCE_USERID_KEY)) {
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_USERID_KEY, LOGGED_OUT);
        }

        if (loggedInUserId == LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }

        if (loggedInUserId == LOGGED_OUT) {
            startActivity(IntentFactory.loginActivityIntentFactory(getApplicationContext()));
            finish();
            return;
        }

        viewModel.getUserByUserId(loggedInUserId).observe(this, user -> {
            if (user != null) {
                updateSharedPreference();
                if (user.isAdmin()) {
                    binding.titleWelcomeTextView.setText("Welcome Admin, " + user.getUsername() + "!");
                    binding.adminButton.setVisibility(View.VISIBLE);
                    binding.adminButton.setOnClickListener(v -> {
                        startActivity(IntentFactory.adminActivityIntentFactory(getApplicationContext()));
                    });
                } else {
                    binding.titleWelcomeTextView.setText("Welcome, " + user.getUsername() + "!");
                    binding.adminButton.setVisibility(View.GONE);
                }
            } else {
                Toast.makeText(this, "Failed to load user", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Sets up the buttons in the main activity.
     * This method initializes the click listeners for the buttons
     * to navigate to different activities such as playing trivia,
     * viewing past questions, and checking the leaderboard.
     *
     */
    private void setupButtons() {
        binding.playTriviaButton.setOnClickListener(v -> {
            startActivity(IntentFactory.questionAnsweringActivityIntentFactory(getApplicationContext(), loggedInUserId));
        });

        binding.pastQuestionsButton.setOnClickListener(v -> {
            //todo: uncomment after past questions is implemented
            //startActivity(IntentFactory.pastQuestionsActivityIntentFactory(getApplicationContext(), loggedInUserId));
        });

        binding.leaderboardButton.setOnClickListener(v -> {
            startActivity(IntentFactory.leaderboardActivityIntentFactory(getApplicationContext()));
        });

        binding.backButton.setOnClickListener(v -> {
            logout();
        });
    }
    private void logout() {
        loggedInUserId = LOGGED_OUT;
        updateSharedPreference();
        getIntent().putExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);

        startActivity(IntentFactory.loginActivityIntentFactory(getApplicationContext()));
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INSTANCE_USERID_KEY, loggedInUserId);
        updateSharedPreference();
    }

    private void updateSharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.preference_userId_key), loggedInUserId);
        editor.apply();
    }



}