package com.group7.g7_trivia_game;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.group7.g7_trivia_game.database.entities.User;
import com.group7.g7_trivia_game.databinding.ActivityLoginBinding;
import com.group7.g7_trivia_game.viewmodels.LoginActivityViewModel;

/**
 * Activity to login the user.
 * Verifies username and password.
 * Has a button to switch to the create account activity.
 *
 * @author Rawley Lizarraga
 * @since 8/2/2025
 */
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel loginViewModel;

    /**
     * Updates binding and viewmodel with initial data.
     * Contains button listeners for logging in and account creation.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        //Button listeners to login or create account
        binding.loginLoginButton.setOnClickListener(v -> {
            Log.d("help", "button pressed");
            verifyUser();
        });

        binding.loginCreateAccountButton.setOnClickListener(v -> {
            createAccount();
        });
    }

    /**
     * Method to verify username and password.
     * If both are successfully verified, start MainActivity and send the userId with the application context.
     * If either fail, create a toast with an error message.
     */
    private void verifyUser() {
        String username = binding.loginUsernameEditText.getText().toString().trim();
        String password = binding.loginPasswordEditText.getText().toString().trim();

        LiveData<User> userLiveData = loginViewModel.getUserByUsername(username);
        Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null && user.getPassword().equals(password)) {
                    Log.d("help", "activity started");
                    Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();
                    startActivity(IntentFactory.mainActivityIntentFactory(getApplicationContext(), user.getId()));

                    // get me out of this god forsaken observer i've been getting multiple intents for so long
                    userLiveData.removeObserver(this);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    userLiveData.removeObserver(this);
                }
            }
        };
        userLiveData.observe(this, userObserver);
    }

    /**
     * Method to start the account creation activity.
     */
    private void createAccount() {
        startActivity(IntentFactory.createAccountActivityIntentFactory(getApplicationContext()));
    }
}