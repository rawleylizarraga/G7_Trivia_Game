package com.group7.g7_trivia_game.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.group7.g7_trivia_game.database.entities.AnsweredQuestion;
import com.group7.g7_trivia_game.database.entities.Question;
import com.group7.g7_trivia_game.database.entities.User;

import java.util.List;

/**
 * description
 *
 * @author
 * @since 7/31/2025
 */
public class TriviaRepository {
    private UserDao mUserDao;
    private QuestionDao mQuestionDao;
    private AnsweredQuestionDao mAnsweredQuestionDao;

    TriviaRepository(Application application){
        TriviaDatabase db =TriviaDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mQuestionDao = db.questionDao();
        mAnsweredQuestionDao = db.answeredQuestionDao();
    }

    public void insertUser(User user) {
        TriviaDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

    public void insertQuestion(Question question) {
        TriviaDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionDao.insert(question);
        });
    }

    public void insertAnsweredQuestion(AnsweredQuestion answeredQuestion) {
        TriviaDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(answeredQuestion);
        });
    }

    public LiveData<User> getUserByUsername(String username) {
        return mUserDao.getUserByUserName(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return mUserDao.getUserByUserId(userId);
    }

    public LiveData<User> getQuestionByQuestionId(int questionId) {
        return mQuestionDao.getQuestionByQuestionId(questionId);
    }

    public LiveData<User> getAnsweredQuestion(int questionId, int userId) {
        return mAnsweredQuestionDao.getAnsweredQuestion(questionId, userId);
    }

    public LiveData<List<AnsweredQuestion>> getAllAnsweredQuestionsByUserId(int userId) {
        return mAnsweredQuestionDao.getAllAnsweredQuestionsByUserId(userId);
    }
}
