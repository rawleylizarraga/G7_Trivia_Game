package com.group7.g7_trivia_game.database.entities;

import androidx.room.Entity;

import com.group7.g7_trivia_game.database.TriviaDatabase;

/**
 * description
 *
 * @author
 * @since 7/31/2025
 */
@Entity(tableName = TriviaDatabase.ANSWERED_QUESTION_TABLE)
public class AnsweredQuestion {
    private int questionId;
}
