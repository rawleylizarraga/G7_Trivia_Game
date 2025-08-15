package com.group7.g7_trivia_game.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.group7.g7_trivia_game.database.TriviaRepository;
import com.group7.g7_trivia_game.database.entities.Question;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private final TriviaRepository repository;

    /**
     * Constructor for QuestionViewModel.
     * @param application Application context
     */
    public QuestionViewModel(Application application) {
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
     * Inserts a answered question into the database.
     * @param question Question to be inserted
     * @param userId User ID
     */
    public void insertAnsweredQuestion(int questionId, int userId) {
        repository.insertAnsweredQuestion(questionId, userId);
    }

    /**
     * Updates the user's score.
     * @param userId User ID
     * @param points Points to be added
     */
    public void updateUserScore(int userId, int points) {
        repository.updateUserScore(userId, points);
    }

    /**
     * Inserts a new question into the database.
     * @param q Question to be inserted
     */
    public void insertQuestion(Question q) {
            repository.insertQuestion(q);
    }

    public LiveData<List<Integer>> getAllAnsweredQuestionIdsByUserId(int userId) {
        return repository.getAllAnsweredQuestionIdsByUserId(userId);
    }
}
