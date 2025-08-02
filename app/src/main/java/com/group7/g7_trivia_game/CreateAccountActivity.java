package com.group7.g7_trivia_game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.group7.g7_trivia_game.databinding.ActivityCreateAccountBinding;
import com.group7.g7_trivia_game.viewmodels.CreateAccountActivityViewModel;

/**
 * Account Creation activity that takes username and password input.
 * Verifies if the user already exists before creating a new account.
 *
 * @author Rawley Lizarraga
 * @since 8/2/2025
 */
public class CreateAccountActivity extends AppCompatActivity {
    private ActivityCreateAccountBinding binding;
    private CreateAccountActivityViewModel createAccountViewModel;

    /**
     * Sets binding and createAccountViewModel using context.
     * Has button listeners for account creation and returning to login screen.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createAccountViewModel = new ViewModelProvider(this).get(CreateAccountActivityViewModel.class);

        //Button listeners for returning to the login screen or creating a new account
        binding.createAccountBackButton.setOnClickListener(v -> {
            returnToLogin();
        });

        binding.createAccountCreateAccountButton.setOnClickListener(v -> {
            createAccount();
        });
    }

    private void returnToLogin() {
        startActivity(IntentFactory.loginActivityIntentFactory(getApplicationContext()));
    }
}