package com.group7.g7_trivia_game;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.group7.g7_trivia_game.databinding.ActivityQuestionAnsweringBinding;
import com.group7.g7_trivia_game.viewmodels.QuestionViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuestionAnsweringActivity extends AppCompatActivity {

    private QuestionViewModel questionViewModel;
    private ActivityQuestionAnsweringBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answering);
        binding = ActivityQuestionAnsweringBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Initialize the view model
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        questionViewModel.getAllQuestionIds().observe(this, allIds -> {
            if (allIds != null) {
                List<Integer> allIdsList = new ArrayList<>(allIds); // Convert to regular list

                questionViewModel.getAllAnsweredQuestionIds().observe(this, answeredIds -> {
                    if (answeredIds != null) {
                        List<Integer> answeredIdsList = new ArrayList<>(answeredIds); // Convert to regular list

                        // Now both as regular lists
                        List<Integer> filteredIds = filterUnanswered(allIdsList, answeredIdsList);

                        // Get a random unanswered question
                        questionViewModel.getRandomQuestionFromIds(filteredIds).observe(this, question -> {
                            if (question != null) {
                                binding.questionTextView.setText(question.getQuestion());

                                // Create a list of all answer options
                                List<String> answers = new ArrayList<>();
                                answers.add(question.getAnswerCorrect());
                                answers.add(question.getAnswerWrong1());
                                answers.add(question.getAnswerWrong2());
                                answers.add(question.getAnswerWrong3());

                                // Shuffle the answers
                                Collections.shuffle(answers);

                                // Set button text
                                binding.answerOneButton.setText(answers.get(0));
                                binding.answerTwoButton.setText(answers.get(1));
                                binding.answerThreeButton.setText(answers.get(2));
                                binding.answerFourButton.setText(answers.get(3));

                            } else {
                                binding.questionTextView.setText("No unanswered questions found.");
                            }
                        });
                    }
                });
            }
        });
            }


    // Helper function to remove answered IDs from the full list
    private List<Integer> filterUnanswered(List<Integer> all, List<Integer> answered) {
        List<Integer> copy = new ArrayList<>(all);
        copy.removeAll(answered);
        return copy;
    }

}