package com.group7.g7_trivia_game.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

/**
 * description
 *
 * @author
 * @since 7/31/2025
 */
@Dao
public interface AnsweredQuestionDao {

    // Retrieves a list of all question IDs in the table
    @Query("SELECT questionId FROM " + TriviaDatabase.ANSWERED_QUESTION_TABLE)
    LiveData<List<Integer>> getAllAnsweredQuestionIds();

}
