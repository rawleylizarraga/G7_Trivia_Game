package com.group7.g7_trivia_game.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.group7.g7_trivia_game.database.entities.Question;

import java.util.List;

/**
 * description
 *
 * @author
 * @since 7/31/2025
 */
@Dao
public interface QuestionDao {

    // Returns a random question from the entire table
    @Query("SELECT * FROM " + TriviaDatabase.QUESTION_TABLE + " ORDER BY RANDOM() LIMIT 1")
    LiveData<Question> getAnyRandomQuestion();

    // Inserts a new question into the database
    @Insert
    void insert(Question question);

    // Retrieves a list of all question IDs in the table
    @Query("SELECT questionId FROM " + TriviaDatabase.QUESTION_TABLE)
    LiveData<List<Integer>> getAllQuestionIds();

    // Returns a random question from a specific list of question IDs
    @Query("SELECT * FROM " + TriviaDatabase.QUESTION_TABLE + " WHERE questionId IN (:ids) ORDER BY RANDOM() LIMIT 1")
    LiveData<Question> getRandomQuestionFromIds(List<Integer> ids);


}
