package com.group7.g7_trivia_game.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.group7.g7_trivia_game.database.TriviaRepository;
import com.group7.g7_trivia_game.database.entities.User;

import java.util.List;

public class LeaderboardViewModel extends AndroidViewModel {
    private final TriviaRepository repository;

    public LeaderboardViewModel(Application application) {
        super(application);
        repository = TriviaRepository.getRepository(application); // Method in repo
    }

    public LiveData<List<User>> getTopUsers() {
        return repository.getTopUsersByScore(); // method in the repo
    }


}
