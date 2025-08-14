package com.group7.g7_trivia_game.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.group7.g7_trivia_game.database.entities.Question;

import java.util.List;

/**
 * Interface for the QuestionDao.
 *
 * @author Marco Lara
 * @since 7/31/2025
 */
@Dao
public interface QuestionDao {

    /**
     * Inserts a new question into the database.
     * @param question Question to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Question question);

    /**
     * Updates an existing question in the database.
     * @param question Question to be updated
     */
    @Update
    void update(Question question);

    /**
     * Deletes a question from the database.
     * @param question Question to be deleted
     */
    @Delete
    void delete(Question question);

    /**
     * Gets a list of all question IDs.
     * @return List of all question IDs
     */
    @Query("SELECT questionId FROM " + TriviaDatabase.QUESTION_TABLE)
    LiveData<List<Integer>> getAllQuestionIds();

    /**
     * Gets a list of all answered question IDs.
     * @param ids List of all question IDs
     * @return List of all answered question IDs
     */
    @Query("SELECT * FROM " + TriviaDatabase.QUESTION_TABLE + " WHERE questionId IN (:ids) ORDER BY RANDOM() LIMIT 1")
    LiveData<Question> getRandomQuestionFromIds(List<Integer> ids);

    /**
     * Gets a question by its questions text.
     * @param question Text of the question
     * @return Question with the given text
     */
    @Query("SELECT * FROM " + TriviaDatabase.QUESTION_TABLE + " WHERE question == :question")
    Question getQuestionByTextSynchronous(String question);
}
