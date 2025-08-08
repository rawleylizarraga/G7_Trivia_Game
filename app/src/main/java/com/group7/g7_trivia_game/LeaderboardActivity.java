package com.group7.g7_trivia_game;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.group7.g7_trivia_game.databinding.ActivityLeaderboardBinding;
import com.group7.g7_trivia_game.viewmodels.LeaderboardAdapter;
import com.group7.g7_trivia_game.viewmodels.LeaderboardViewModel;

import java.util.ArrayList;

/**
 * LeaderboardActivity class
 * This activity displays the leaderboard for the trivia game.
 * @author Madison Nolen
 * @since 8/4/2025
 */

public class LeaderboardActivity extends AppCompatActivity {

    private ActivityLeaderboardBinding binding;
    private LeaderboardViewModel viewModel;
    private LeaderboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LeaderboardViewModel.class);

        // Setup RecyclerView
        adapter = new LeaderboardAdapter(new ArrayList<>());
        binding.leaderboardRecyclerView.setAdapter(adapter);
        binding.leaderboardRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getTopUsers().observe(this, users -> {
            adapter.setUserList(users); // List is already sorted by score
        });

        // Back button returns to MainActivity
        binding.backButton.setOnClickListener(v -> {
            finish(); // Ends this activity and returns to the previous
        });
    }
}