package com.group7.g7_trivia_game.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.group7.g7_trivia_game.database.entities.AnsweredQuestion;
import com.group7.g7_trivia_game.database.entities.User;
import java.util.List;

/**
 * description
 *
 * @author Madison Nolen
 * @since 8/5/2025
 */
@Dao
public interface AnsweredQuestionDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AnsweredQuestion answeredQuestion);

    @Delete
    void delete(AnsweredQuestion answeredQuestion);

    @Query("DELETE FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE)
    void deleteAll();

    // Get all answered questions by a user
    @Query("SELECT * FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE + " WHERE userId = :userId")
    LiveData<List<AnsweredQuestion>> getAnsweredQuestionsByUser(int userId);

    // Get all answered questions (admin view)
    @Query("SELECT * FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE)
    LiveData<List<AnsweredQuestion>> getAllAnsweredQuestions();

    // Get a specific answered question by questionId and userId
    @Query("SELECT * FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE + " WHERE questionId = :questionId AND userId = :userId")
    LiveData<AnsweredQuestion> getAnsweredQuestion(int questionId, int userId);

    // Get all answered questions by userId, ordered by dateAnswered
    @Query("SELECT * FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE + " WHERE userId = :userId ORDER BY dateAnswered DESC")
    LiveData<List<AnsweredQuestion>> getAllAnsweredQuestionsByUserId(int userId);

    // Retrieves a list of all question IDs in the table
    @Query("SELECT questionId FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE)
    LiveData<List<Integer>> getAllAnsweredQuestionIds();

    // Retrieves a random answered question from the list of IDs
    @Query("SELECT * FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE + " WHERE answeredQuestionId == :answeredQuestionId")
    AnsweredQuestion getAnsweredQuestionByIdSynchronous(int answeredQuestionId);

    // Retrieves a list of all question IDs in the table for a specific user
    @Query("SELECT questionId FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE + " WHERE userId = :userId")
    LiveData<List<Integer>> getAllAnsweredQuestionIdsByUserId(int userId);

}
