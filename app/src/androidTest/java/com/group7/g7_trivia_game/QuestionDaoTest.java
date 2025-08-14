package com.group7.g7_trivia_game;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.group7.g7_trivia_game.database.QuestionDao;
import com.group7.g7_trivia_game.database.TriviaDatabase;
import com.group7.g7_trivia_game.database.entities.Question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Question table tests (Insert, Update, Delete)
 *
 * @author Marco Lara
 * @since 8/13/2025
 */

public class QuestionDaoTest {
    private QuestionDao questionDao;
    private TriviaDatabase db;

    // Sample data for testing
    private final String qTest = "What is 2+2?";
    private final String correct = "4";
    private final String w1 = "5";
    private final String w2 = "6";
    private final String w3 = "7";
    private final String cat = "Math";
    private final int points = 1;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TriviaDatabase.class).build();
        questionDao = db.questionDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    private Question createQuestion(String text) {
        Question q = new Question();
        q.setQuestion(text);
        q.setAnswerCorrect(correct);
        q.setAnswerWrong1(w1);
        q.setAnswerWrong2(w2);
        q.setAnswerWrong3(w3);
        q.setCategory(cat);
        q.setPoints(points);
        return q;
    }

    @Test
    public void insertQuestionTest() {

        // ensure it doesn't exist first
        Question retrievedQuestion = questionDao.getQuestionByTextSynchronous(qTest);
        assertThat(retrievedQuestion, equalTo(null));

        // insert the question
        questionDao.insert(createQuestion(qTest));

        // verify
        retrievedQuestion = questionDao.getQuestionByTextSynchronous(qTest);
        assertThat(retrievedQuestion.getQuestion(), equalTo(qTest));
        assertThat(retrievedQuestion.getAnswerCorrect(), equalTo(correct));
        assertThat(retrievedQuestion.getAnswerWrong1(), equalTo(w1));
        assertThat(retrievedQuestion.getAnswerWrong2(), equalTo(w2));
        assertThat(retrievedQuestion.getAnswerWrong3(), equalTo(w3));
        assertThat(retrievedQuestion.getCategory(), equalTo(cat));
        assertThat(retrievedQuestion.getPoints(), equalTo(points));
    }

    @Test
    public void updateQuestionTest() {

        // insert the question
        questionDao.insert(createQuestion(qTest));

        // modify the question
        Question retrievedQuestion = questionDao.getQuestionByTextSynchronous(qTest);
        String newText = "What is 1+1?";
        String newCorrect = "2";
        retrievedQuestion.setQuestion(newText);
        retrievedQuestion.setAnswerCorrect(newCorrect);

        // update the question
        questionDao.update(retrievedQuestion);

        // verify
        Question updatedQuestion = questionDao.getQuestionByTextSynchronous(newText);
        assertThat(updatedQuestion.getQuestion(), equalTo(newText));
        assertThat(updatedQuestion.getAnswerCorrect(), equalTo(newCorrect));

        assertThat(questionDao.getQuestionByTextSynchronous(qTest), equalTo(null));
    }

    @Test
    public void deleteQuestionTest() {

        // insert the question
        questionDao.insert(createQuestion(qTest));

        // confirm it exists
        Question retrievedQuestion = questionDao.getQuestionByTextSynchronous(qTest);

        // delete the question
        questionDao.delete(retrievedQuestion);

        // verify
        retrievedQuestion = questionDao.getQuestionByTextSynchronous(qTest);
        assertThat(retrievedQuestion, equalTo(null));

    }

}
