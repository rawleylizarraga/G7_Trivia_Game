package com.group7.g7_trivia_game.database;

import android.app.Application;

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
}
