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

    /**
     * Constructor for QuestionViewModel.
     * @param application Application context
     */
    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repository = new TriviaRepository(application);
    }

    /**
     * Gets a list of all question IDs.
     * @return List of all question IDs
     */
    public LiveData<List<Integer>> getAllQuestionIds() {
        return repository.getAllQuestionIds();
    }

    /**
     * Gets a list of all answered question IDs.
     * @return List of all answered question IDs
     */
    public LiveData<List<Integer>> getAllAnsweredQuestionIds() {
        return repository.getAllAnsweredQuestionIds();
    }

    /**
     * Gets a random question from the list of all questions.
     * @param ids List of all question IDs
     * @return Random question
     */
    public LiveData<Question> getRandomQuestionFromIds(List<Integer> ids) {
        return repository.getRandomQuestionFromIds(ids);
    }

    /**
     * Inserts a new question into the database.
     * @param question Question to be inserted
     */
    public void insertAnsweredQuestion(int questionId, int userId) {
        //TODO: Implement this method
    }

    /**
     * Updates the user's score.
     * @param userId User ID
     * @param points Points to be added
     */
    public void updateUserScore(int userId, int points) {
        //TODO: Implement this method
    }
}
