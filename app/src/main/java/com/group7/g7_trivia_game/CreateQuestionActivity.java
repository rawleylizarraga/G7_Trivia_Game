package com.group7.g7_trivia_game;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

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

    /**
     * Creates a new question and inserts it into the database.
     */
    private void createQuestion() {
        String questionText = binding.createQuestionEditText.getText().toString().trim();
        String correctAnswer = binding.correctAnswerEditText.getText().toString().trim();
        String wrongAnswer1 = binding.wrongAnswer1EditText.getText().toString().trim();
        String wrongAnswer2 = binding.wrongAnswer2EditText.getText().toString().trim();
        String wrongAnswer3 = binding.wrongAnswer3EditText.getText().toString().trim();
        String category = binding.categoryEditText.getText().toString().trim();
        String points = binding.pointsEditText.getText().toString().trim();

        if (questionText.isEmpty() || correctAnswer.isEmpty() || wrongAnswer1.isEmpty() || wrongAnswer2.isEmpty() || wrongAnswer3.isEmpty() || category.isEmpty() || points.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        questionViewModel.insertQuestion(questionText, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3, category, points);
        Toast.makeText(this, "Question created!", Toast.LENGTH_SHORT).show();
        returnToMain();

    }

    /**
     * Returns to the main activity.
     */
    private void returnToMain() {
        startActivity(IntentFactory.mainActivityIntentFactory(getApplicationContext(), userId));
    }
}