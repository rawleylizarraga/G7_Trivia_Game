
/**
 * Activity to display past trivia questions.
 * @author Cristina Pizano
 * @since 8/2/2025
 */
package com.group7.g7_trivia_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group7.g7_trivia_game.adapters.PastQuestionsAdapter;
import com.group7.g7_trivia_game.viewmodels.PastQuestionsViewModel;

public class PastQuestionsActivity extends AppCompatActivity {

    private PastQuestionsViewModel viewModel;
    private PastQuestionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_questions);

        // RecyclerView setup
        RecyclerView rv = findViewById(R.id.rvPastQuestions);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PastQuestionsAdapter();
        rv.setAdapter(adapter);

        // Buttons
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnChangeCategory).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // TODO: make sure created ChooseCategoryActivity before enabling this
                // startActivity(new Intent(PastQuestionsActivity.this, ChooseCategoryActivity.class));
            }
        });

        // ViewModel + LiveData
        viewModel = new ViewModelProvider(this).get(PastQuestionsViewModel.class);
        viewModel.getPastQuestions().observe(this, questions -> {
            // Update list when data changes
            adapter.submit(questions);
        });
    }
}
