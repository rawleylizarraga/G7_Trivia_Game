package com.group7.g7_trivia_game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.group7.g7_trivia_game.databinding.ActivityQuestionAnsweringBinding;
import com.group7.g7_trivia_game.viewmodels.QuestionViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuestionAnsweringActivity extends AppCompatActivity {

    private QuestionViewModel questionViewModel;
    private ActivityQuestionAnsweringBinding binding;

    /**
     * Updates binding and viewmodel with initial data.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionAnsweringBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //Initialize the view model
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        //Get the user id from the intent
        int userId = getIntent().getIntExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);

        //Observe the list of all question IDs
        questionViewModel.getAllQuestionIds().observe(this, allIds -> {
            if (allIds != null) {
                List<Integer> allIdsList = new ArrayList<>(allIds); // Convert to regular list

                //Observe the list of answered question IDs
                questionViewModel.getAllAnsweredQuestionIds().observe(this, answeredIds -> {
                    if (answeredIds != null) {
                        List<Integer> answeredIdsList = new ArrayList<>(answeredIds); // Convert to regular list

                        // Filter out answered questions
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

                                // Set click listeners for buttons
                                View.OnClickListener buttonClickListener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String selected = ((Button) v).getText().toString();
                                        String correct = question.getAnswerCorrect();

                                        if (selected.equals(correct)) {
                                            Toast.makeText(QuestionAnsweringActivity.this,"Yay! You answered correctly.", Toast.LENGTH_SHORT).show();
                                            questionViewModel.updateUserScore(userId, question.getPoints());
                                        } else {
                                            Toast.makeText(QuestionAnsweringActivity.this,"Wrong answer.", Toast.LENGTH_SHORT).show();
                                        }

                                        // Mark question as answered
                                        questionViewModel.insertAnsweredQuestion(question.getQuestionId(), userId);

                                        //restart the activity
                                        recreate();
                                    }
                                };

                                // Attach click listeners to buttons
                                binding.answerOneButton.setOnClickListener(buttonClickListener);
                                binding.answerTwoButton.setOnClickListener(buttonClickListener);
                                binding.answerThreeButton.setOnClickListener(buttonClickListener);
                                binding.answerFourButton.setOnClickListener(buttonClickListener);

                            } else {
                                binding.questionTextView.setText("No unanswered questions found.");
                            }
                        });
                    }
                });
            }
        });
            }


    /**
     * Filters out answered questions from the list of all questions.
     * @param all List of all question IDs
     * @param answered List of answered question IDs
     * @return List of unanswered questions
     */
    private List<Integer> filterUnanswered(List<Integer> all, List<Integer> answered) {
        List<Integer> copy = new ArrayList<>(all);
        copy.removeAll(answered);
        return copy;
    }

}