package com.group7.g7_trivia_game.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.group7.g7_trivia_game.database.TriviaDatabase;

/**
 * User object for storing in database.
 *
 * @author Rawley Lizarraga
 * @since 7/31/2025
 */
@Entity(tableName = TriviaDatabase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;
    private int score;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
        this.score = 0;
    }
}
