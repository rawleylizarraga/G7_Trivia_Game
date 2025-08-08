package com.group7.g7_trivia_game;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group7.g7_trivia_game.databinding.ActivityCreateQuestionBinding;
import com.group7.g7_trivia_game.viewmodels.QuestionViewModel;

public class CreateQuestionActivity extends AppCompatActivity {

    private int userId;
    private ActivityCreateQuestionBinding binding;
    private QuestionViewModel questionViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userId = getIntent().getIntExtra("userId", -1);

        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        //Button listeners for returning to the main activity
        binding.createQuestionBackButton.setOnClickListener(v -> {
            returnToMain();
        });

        binding.createQuestionSubmitButton.setOnClickListener(v -> {
            createQuestion();
        });

    }

    private void returnToMain() {
        startActivity(IntentFactory.mainActivityIntentFactory(getApplicationContext(), userId));
    }
}