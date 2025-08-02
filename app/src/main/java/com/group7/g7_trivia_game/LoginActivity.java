package com.group7.g7_trivia_game;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
    }
}