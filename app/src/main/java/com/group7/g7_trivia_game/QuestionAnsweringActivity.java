package com.group7.g7_trivia_game;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group7.g7_trivia_game.databinding.ActivityQuestionAnsweringBinding;


public class QuestionAnsweringActivity extends AppCompatActivity {

    private ActivityQuestionAnsweringBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answering);
        binding = ActivityQuestionAnsweringBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //displays the question
        binding.questionTextView.setText("");

    }
}