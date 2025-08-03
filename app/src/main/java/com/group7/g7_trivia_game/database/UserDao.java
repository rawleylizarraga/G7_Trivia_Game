package com.group7.g7_trivia_game.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.group7.g7_trivia_game.database.entities.User;

/**
 * Data access object for User.
 * Referenced by the repository to allow accessing User objects.
 *
 * @author Rawley Lizarraga
 * @since 7/31/2025
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM " + TriviaDatabase.USER_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + TriviaDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUserName(String username);

    @Query("SELECT * FROM " + TriviaDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);
}
