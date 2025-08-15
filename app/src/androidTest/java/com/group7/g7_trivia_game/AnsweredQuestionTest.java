package com.group7.g7_trivia_game;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.group7.g7_trivia_game.database.AnsweredQuestionDao;
import com.group7.g7_trivia_game.database.TriviaDatabase;
import com.group7.g7_trivia_game.database.entities.AnsweredQuestion;
import com.group7.g7_trivia_game.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * AnsweredQuestion table tests (Insert, Delete, Get by User ID)
 * This class tests the AnsweredQuestionDao methods for inserting, deleting,
 * and retrieving answered questions by user ID.
 *
 * @author Madison Nolen
 * @since 8/13/2025
 */
@RunWith(AndroidJUnit4.class)
public class AnsweredQuestionTest {
    private AnsweredQuestionDao answeredQuestionDao;
    private TriviaDatabase db;

    int answeredQuestionId = 1;
    int userId = 1;
    int questionId = 2;
    String dateAnswered = "2025-08-13T12:00:00Z";
    int numberOfTries = 0;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TriviaDatabase.class)
                .allowMainThreadQueries()
                .build();
        answeredQuestionDao = db.answeredQuestionDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertTest() {
        AnsweredQuestion answeredQuestion = new AnsweredQuestion(answeredQuestionId, userId, questionId, dateAnswered, numberOfTries);
        AnsweredQuestion retrievedAnsweredQuestion;

        // Make sure the answered question doesn't exist first
        retrievedAnsweredQuestion = answeredQuestionDao.getAnsweredQuestionByIdSynchronous(answeredQuestionId);
        assertThat(retrievedAnsweredQuestion, equalTo(null));

        // Add the answered question to the database
        answeredQuestionDao.insert(answeredQuestion);

        // Get the answered question by ID
        retrievedAnsweredQuestion = answeredQuestionDao.getAnsweredQuestionByIdSynchronous(answeredQuestionId);
        assertThat(retrievedAnsweredQuestion.getAnsweredQuestionId(), equalTo(answeredQuestionId));
    }

    @Test
    public void deleteTest() {
        AnsweredQuestion answeredQuestion = new AnsweredQuestion(answeredQuestionId, userId, questionId, dateAnswered, numberOfTries);
        AnsweredQuestion retrievedAnsweredQuestion;

        // Add the answered question to the database
        answeredQuestionDao.insert(answeredQuestion);

        // Get the answered question by ID
        retrievedAnsweredQuestion = answeredQuestionDao.getAnsweredQuestionByIdSynchronous(answeredQuestionId);
        assertThat(retrievedAnsweredQuestion.getAnsweredQuestionId(), equalTo(answeredQuestionId));

        // Delete the answered question
        answeredQuestionDao.delete(answeredQuestion);

        // Try to get the deleted answered question
        retrievedAnsweredQuestion = answeredQuestionDao.getAnsweredQuestionByIdSynchronous(answeredQuestionId);
        assertThat(retrievedAnsweredQuestion, equalTo(null));
    }

    @Test
    public void getAnsweredQuestionByUserIdTest() {
        AnsweredQuestion answeredQuestion = new AnsweredQuestion(answeredQuestionId, userId, questionId, dateAnswered, numberOfTries);
        answeredQuestionDao.insert(answeredQuestion);

        // Retrieve answered questions by userId
        AnsweredQuestion retrievedAnsweredQuestion = answeredQuestionDao.getAnsweredQuestionByIdSynchronous(answeredQuestionId);
        assertThat(retrievedAnsweredQuestion.getUserId(), equalTo(userId));
    }

    @Test
    public void updateTest() {

        AnsweredQuestion answeredQuestion = new AnsweredQuestion(
                answeredQuestionId, userId, questionId, dateAnswered, numberOfTries
        );
        AnsweredQuestion retrievedAnsweredQuestion;

        // Make sure the answered question doesn't exist first
        retrievedAnsweredQuestion = answeredQuestionDao.getAnsweredQuestionByIdSynchronous(answeredQuestionId);
        assertThat(retrievedAnsweredQuestion, equalTo(null));

        // Add the answered question to the database
        answeredQuestionDao.insert(answeredQuestion);

        // Get the answered question by ID
        retrievedAnsweredQuestion = answeredQuestionDao.getAnsweredQuestionByIdSynchronous(answeredQuestionId);
        assertThat(retrievedAnsweredQuestion.getAnsweredQuestionId(), equalTo(answeredQuestionId));

        // Update the number of tries
        int newNumberOfTries = 3;
        retrievedAnsweredQuestion.setNumberOfTries(newNumberOfTries);
        answeredQuestionDao.update(retrievedAnsweredQuestion);

        // Get the updated answered question from the database and check the new value
        AnsweredQuestion updatedAnsweredQuestion =
                answeredQuestionDao.getAnsweredQuestionByIdSynchronous(answeredQuestionId);
        assertThat(updatedAnsweredQuestion.getNumberOfTries(), equalTo(newNumberOfTries));

        // Ensure the change actually replaced the old value
        assertThat(updatedAnsweredQuestion.getNumberOfTries(), equalTo(3));
    }


}
