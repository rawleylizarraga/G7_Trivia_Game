package com.group7.g7_trivia_game.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.group7.g7_trivia_game.database.TriviaRepository;
import com.group7.g7_trivia_game.database.entities.User;

import java.util.List;
/**
 * View model for the leaderboard to access the repository.
 * This class provides methods to retrieve the top users based on their scores.
 * It extends AndroidViewModel to allow access to the application context.
 *
 * @author Madison Nolen
 * @since 8/5/2025
 */
public class LeaderboardViewModel extends AndroidViewModel {
    private final TriviaRepository repository;

    public LeaderboardViewModel(Application application) {
        super(application);
        repository = new TriviaRepository(application); // Method in repo
    }

    public LiveData<List<User>> getTopUsers() {
        return repository.getTopUsersByScore(); // method in the repo
    }


}
