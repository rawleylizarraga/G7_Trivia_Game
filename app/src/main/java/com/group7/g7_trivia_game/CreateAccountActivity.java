package com.group7.g7_trivia_game;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.group7.g7_trivia_game.database.entities.User;
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

    /**
     * Returns to the login screen by calling the intent factory for the login activity.
     */
    private void returnToLogin() {
        startActivity(IntentFactory.loginActivityIntentFactory(getApplicationContext()));
    }

    /**
     * Verifies that the entered username and password are valid.
     * Then checks if the username already exists by using getUserByUsername and checking if result is void.
     * If the username does not belong to an existing User, then a new User is created with the given username and password.
     * After the new User is created, the MainActivity/Landing page is called by its intent factory with the new User's userId.
     */
    private void createAccount() {
        String username = binding.createAccountUsernameEditText.getText().toString().trim();
        String password = binding.createAccountPasswordEditText.getText().toString().trim();
        String passwordConfirm = binding.createAccountConfirmEditText.getText().toString().trim();

        //Verify that then input username and password are not empty and that the passwords match.
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (username.length() > 12) {
            Toast.makeText(this, "Username must me 10 characters or shorter", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(passwordConfirm)) {
            Toast.makeText(this, "Please confirm the password", Toast.LENGTH_SHORT).show();
            return;
        }

        //Use observers to check if the input username is already taken by seeing if getUserByUsername returns a User (already taken) or null (not taken)
        LiveData<User> userLiveData = createAccountViewModel.getUserByUsername(username);
        Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    Toast.makeText(CreateAccountActivity.this, "Username is taken", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Creating account", Toast.LENGTH_SHORT).show();
                    User newUser = new User(username, password);
                    createAccountViewModel.insertUser(newUser);

                    //Uses an observer to ensure that the userId is successfully retrieved before starting the MainActivity/Landing page
                    createAccountViewModel.getUserByUsername(username).observe(CreateAccountActivity.this, userCheck -> {
                        if (userCheck != null) {
                            startActivity(IntentFactory.mainActivityIntentFactory(getApplicationContext(), userCheck.getId()));
                        } //else {
                            //Toast.makeText(CreateAccountActivity.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                        //}
                    });
                }
                userLiveData.removeObserver(this);
            }
        };
        userLiveData.observe(this, userObserver);
    }
}