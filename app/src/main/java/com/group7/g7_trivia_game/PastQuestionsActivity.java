
/**
 * Activity to display past trivia questions.
 *
 * @author Cristina Pizano
 * @since 8/2/2025
 */
package com.group7.g7_trivia_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group7.g7_trivia_game.adapters.PastQuestionsAdapter;
import com.group7.g7_trivia_game.database.entities.AnsweredQuestion;
import com.group7.g7_trivia_game.database.entities.Question;
import com.group7.g7_trivia_game.viewmodels.PastQuestionsViewModel;

import java.util.ArrayList;
import java.util.List;

public class PastQuestionsActivity extends AppCompatActivity {

    private PastQuestionsViewModel viewModel;
    private PastQuestionsAdapter adapter;
    private int userId;
    private String categoryFilter = null;
    public static List<String> questionString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_questions);

        //userId = getIntent().getIntExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);
        userId = getIntent().getIntExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);

        // RecyclerView setup
        RecyclerView rv = findViewById(R.id.rvPastQuestions);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PastQuestionsAdapter();
        rv.setAdapter(adapter);

        // Buttons
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnChangeCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(PastQuestionsActivity.this, ChooseCategoryActivity.class));
                startActivity(IntentFactory.chooseCategoryActivityIntentFactory(getApplicationContext()));
            }
        });

        // ViewModel + LiveData
        viewModel = new ViewModelProvider(this).get(PastQuestionsViewModel.class);
        viewModel.getPastQuestions().observe(this, questions -> {
            // Update list when data changes
            adapter.submit(questions);
        });


        questionsList();
    }

    public void questionsList() {

        viewModel.getAllAnsweredQuestionIdsByUserId(userId).observe(this, answeredIds -> {
            if (answeredIds != null) {
                List<Integer> answeredIdsList = new ArrayList<>(answeredIds); // Convert to regular list

                for(Integer answeredQuestionId : answeredIdsList){
                    viewModel.getAnsweredQuestionByAnsweredQuestionId(answeredQuestionId).observe(this, nextQuestion -> {
                        if (nextQuestion != null) {
                            int  questionId = nextQuestion.getQuestionId();

                            viewModel.getQuestionByQuestionId(questionId).observe(this, question -> {
                                if (question != null) {
                                    questionString.add(question.getQuestion());
                                }
                            });
                        }
                    });

                }
            }
        });

        viewModel.pastQuestions.setValue(questionString);
    }
}
