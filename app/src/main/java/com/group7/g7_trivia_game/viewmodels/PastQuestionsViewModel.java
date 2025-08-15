/**
 * ViewModel for managing past trivia questions in the G7 Trivia Game app.
 * @author Cristina Pizano
 * @since 7/31/2025
 */

package com.group7.g7_trivia_game.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.group7.g7_trivia_game.PastQuestionsActivity;
import com.group7.g7_trivia_game.database.TriviaRepository;
import com.group7.g7_trivia_game.database.entities.AnsweredQuestion;
import com.group7.g7_trivia_game.database.entities.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PastQuestionsViewModel extends AndroidViewModel {

    // Backing property for the list of past questions
    public MutableLiveData<List<String>> pastQuestions = new MutableLiveData<>();
    private final TriviaRepository repository;

    public PastQuestionsViewModel(Application application) {
        super(application);
        repository = new TriviaRepository(application);

        // Temporary sample data for testing and UI display
//        pastQuestions.setValue(Arrays.asList(
//                "What is 2+2?",
//                "What is the capital of France?",
//                "Who wrote 'To Kill a Mockingbird'?"
//        ));
//        pastQuestions.setValue(PastQuestionsActivity.questionString);
    }

    // Public LiveData so UI can observe
    public LiveData<List<String>> getPastQuestions() {
        return pastQuestions;
    }

    public LiveData<List<Integer>> getAllAnsweredQuestionKeysByUserId(int userId) {
        return repository.getAllAnsweredQuestionKeysByUserId(userId);
    }

    public LiveData<AnsweredQuestion> getAnsweredQuestionByAnsweredQuestionId(int answeredQuestionId) {
        return repository.getAnsweredQuestionByAnsweredQuestionId(answeredQuestionId);
    }

    public LiveData<Question> getQuestionByQuestionId(int questionId) {
        return repository.getQuestionByQuestionId(questionId);
    }

}
