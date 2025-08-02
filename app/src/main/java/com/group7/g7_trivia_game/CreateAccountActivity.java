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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createAccountViewModel = new ViewModelProvider(this).get(CreateAccountActivityViewModel.class);


    }
}