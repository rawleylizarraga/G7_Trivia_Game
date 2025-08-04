package com.group7.g7_trivia_game.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.group7.g7_trivia_game.database.QuestionDao;
import com.group7.g7_trivia_game.database.TriviaDatabase;
import com.group7.g7_trivia_game.database.TriviaRepository;
import com.group7.g7_trivia_game.database.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private final TriviaRepository repository;

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repository = new TriviaRepository(application);
    }

    // Expose all question IDs from the DB
    public LiveData<List<Integer>> getAllQuestionIds() {
        return repository.getAllQuestionIds();
    }

    // Expose all answered question IDs
    public LiveData<List<Integer>> getAllAnsweredQuestionIds() {
        return repository.getAllAnsweredQuestionIds();
    }

    // Expose a random question from the DB
    public LiveData<Question> getRandomQuestionFromIds(List<Integer> ids) {
        return repository.getRandomQuestionFromIds(ids);
    }


}
