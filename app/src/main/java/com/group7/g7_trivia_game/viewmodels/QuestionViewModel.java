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
        repository.updateUserScore(userId, points);
    }

    /**
     * Inserts a new question into the database.
     *
     * @param questionText  Question text
     * @param correctAnswer Correct answer
     * @param wrongAnswer1  Wrong answer 1
     * @param wrongAnswer2  Wrong answer 2
     * @param wrongAnswer3  Wrong answer 3
     * @param category      Category of the question
     * @param points        Points of the question
     */
    public void insertQuestion(String questionText, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String category, String points) {
            Question q = new Question();
            q.setQuestion(questionText);
            q.setAnswerCorrect(correctAnswer);
            q.setAnswerWrong1(wrongAnswer1);
            q.setAnswerWrong2(wrongAnswer2);
            q.setAnswerWrong3(wrongAnswer3);
            q.setCategory(category);
            q.setPoints(Integer.parseInt(points));
            repository.insertQuestion(q);
    }
}
