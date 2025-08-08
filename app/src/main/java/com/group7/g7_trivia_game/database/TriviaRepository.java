package com.group7.g7_trivia_game.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.group7.g7_trivia_game.database.entities.AnsweredQuestion;
import com.group7.g7_trivia_game.database.entities.Question;
import com.group7.g7_trivia_game.database.entities.User;

import java.util.List;

/**
 * Repository file for bridging between application and DAOs.
 * Should only be accessed by ViewModels.
 * ADD ADDITIONAL METHODS AS NEEDED
 *
 * @author Rawley Lizarraga
 * @since 7/31/2025
 */
public class TriviaRepository {
    private UserDao mUserDao;
    private QuestionDao mQuestionDao;
    private AnsweredQuestionDao mAnsweredQuestionDao;

    /**
     * Initializes repository with database and DAOs.
     * @param application Application passed by ViewHolder.
     */
    public TriviaRepository(Application application){
        TriviaDatabase db =TriviaDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mQuestionDao = db.questionDao();
        mAnsweredQuestionDao = db.answeredQuestionDao();
    }

    /**
     * Inserts a given {@code User} into the database by calling the DAO.
     * @param user {@code User} to add to the database.
     */
    public void insertUser(User user) {
        TriviaDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

    /**
     * Inserts a given {@code Question} into the database by calling the DAO.
     * @param question {@code Question} to add to the database.
     */
    public void insertQuestion(Question question) {
        TriviaDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionDao.insert(question);
        });
    }

    /**
     * Inserts a given {@code AnsweredQuestion} into the database by calling the DAO.
     * @param answeredQuestion {@code AnsweredQuestion} to add to the database.
     */
    public void insertAnsweredQuestion(AnsweredQuestion answeredQuestion) {
        TriviaDatabase.databaseWriteExecutor.execute(() -> {
            mAnsweredQuestionDao.insert(answeredQuestion);
        });
    }

    /**
     * Retrieves a {@code User} object from the database by finding the provided username.
     * @param username String used to retrieve desired {@code User} object from database.
     * @return {@code LiveData<User>}
     */
    public LiveData<User> getUserByUsername(String username) {
        return mUserDao.getUserByUserName(username);
    }

    /**
     * Retrieves a {@code User} object from the database by finding the provided userId.
     * @param userId String used to retrieve desired {@code User} object from database.
     * @return {@code LiveData<User>}
     */
    public LiveData<User> getUserByUserId(int userId) {
        return mUserDao.getUserByUserId(userId);
    }

    /**
     * Retrieves a {@code Question} object from the database by finding the provided questionId.
     * @param questionId String used to retrieve desired {@code Question} object from database.
     * @return {@code LiveData<Question>}
     */
    public LiveData<User> getQuestionByQuestionId(int questionId) {
        return mQuestionDao.getQuestionByQuestionId(questionId);
    }

    /**
     * Retrieves a {@code AnsweredQuestion} object from the database by finding the provided questionId and userId.
     * @param questionId String used to retrieve desired {@code AnsweredQuestion} object from database.
     * @param userId String used to retrieve desired {@code AnsweredQuestion} object from database.
     * @return {@code LiveData<AnsweredQuestion>}
     */
    public LiveData<User> getAnsweredQuestion(int questionId, int userId) {
        return mAnsweredQuestionDao.getAnsweredQuestion(questionId, userId);
    }

    /**
     * Retrieves a List of all {@code AnsweredQuestion} from the database from the given userId.
     * @param userId String used to retrieve desired {@code AnsweredQuestion} objects from database.
     * @return {@code LiveData<List<AnsweredQuestion>>}
     */
    public LiveData<List<AnsweredQuestion>> getAllAnsweredQuestionsByUserId(int userId) {
        return mAnsweredQuestionDao.getAllAnsweredQuestionsByUserId(userId);
    }


    //ADD ADDITIONAL METHODS AS NEEDED//
    public LiveData<List<User>> getTopUsersByScore() {
        return mUserDao.getTopUsersByScore();
    }


}
