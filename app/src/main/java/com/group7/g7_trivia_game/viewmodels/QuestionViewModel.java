package com.group7.g7_trivia_game.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.group7.g7_trivia_game.database.QuestionDao;
import com.group7.g7_trivia_game.database.TriviaDatabase;
import com.group7.g7_trivia_game.database.entities.Question;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private final QuestionDao questionDao;

    public QuestionViewModel(Application application) {
        super(application);
        TriviaDatabase db = TriviaDatabase.getDatabase(application);
        questionDao = TriviaDatabase.getDatabase(application).questionDao();
    }

    public LiveData<Question> getRandomQuestionFromIds(List<Integer> ids) {
        return questionDao.getRandomQuestionFromIds(ids);
    }


    // Helper function to remove answered IDs from the full list
    private List<Integer> filterUnanswered(List<Integer> all, List<Integer> answered) {
        List<Integer> copy = new ArrayList<>(all);
        copy.removeAll(answered);
        return copy;
    }
}
