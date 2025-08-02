package com.group7.g7_trivia_game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group7.g7_trivia_game.databinding.ActivityCreateAccountBinding;
import com.group7.g7_trivia_game.viewmodels.CreateAccountActivityViewModel;

public class CreateAccountActivity extends AppCompatActivity {
    private ActivityCreateAccountBinding binding;
    private CreateAccountActivityViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}