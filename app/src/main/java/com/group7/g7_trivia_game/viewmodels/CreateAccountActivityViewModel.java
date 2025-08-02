package com.group7.g7_trivia_game.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.group7.g7_trivia_game.database.TriviaRepository;
import com.group7.g7_trivia_game.database.entities.User;

/**
 * View model for the account creation activity.
 *
 * @author Rawley Lizarraga
 * @since 8/2/2025
 */
public class CreateAccountActivityViewModel extends AndroidViewModel {
    private TriviaRepository repository;

    public CreateAccountActivityViewModel(Application application) {
        super(application);
        repository = new TriviaRepository(application);
    }

    public LiveData<User> getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }
}
