package com.group7.g7_trivia_game.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.group7.g7_trivia_game.database.TriviaRepository;
import com.group7.g7_trivia_game.database.entities.User;

/**
 * View model for the main activity to access the repository.
 * @author Madison Nolen
 * @since 8/4/2025
 */
public class MainActivityViewModel extends AndroidViewModel {
    private final TriviaRepository repository;

    public MainActivityViewModel(Application application) {
        super(application);
        repository = new TriviaRepository(application);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return repository.getUserByUserId(userId);
    }
}
