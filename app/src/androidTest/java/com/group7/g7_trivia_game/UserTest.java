package com.group7.g7_trivia_game;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.group7.g7_trivia_game.database.TriviaDatabase;
import com.group7.g7_trivia_game.database.UserDao;
import com.group7.g7_trivia_game.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * User table tests (Insert, Update, Delete)
 *
 * @author Rawley Lizarraga
 * @since 8/13/2025
 */
@RunWith(AndroidJUnit4.class)
public class UserTest {
    private UserDao userDao;
    private TriviaDatabase db;
    String username = "unitTestUser";
    String password = "unitTestPassword";

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TriviaDatabase.class)
                .allowMainThreadQueries()
                .build();
        userDao = db.userDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertUserTest() throws Exception {
        User user = new User(username, password);
        User retrievedUser;

        //make sure the user doesn't exist first
        retrievedUser = userDao.getUserByUserNameSynchronous(username);
        assertThat(retrievedUser, equalTo(null));

        //add the user to the database
        userDao.insert(user);

        //get the user by the name
        retrievedUser = userDao.getUserByUserNameSynchronous(username);

        //make sure it matches
        assertThat(retrievedUser.getUsername(), equalTo(user.getUsername()));
    }

    @Test
    public void deleteUserTest() throws Exception {
        User user = new User(username, password);
        User retrievedUser;

        //make sure the user doesn't exist first
        retrievedUser = userDao.getUserByUserNameSynchronous(username);
        assertThat(retrievedUser, equalTo(null));

        //add the user to the database
        userDao.insert(user);

        //get the user by the name
        retrievedUser = userDao.getUserByUserNameSynchronous(username);

        //make sure it matches
        assertThat(retrievedUser.getUsername(), equalTo(user.getUsername()));

        //delete the user
        userDao.delete(retrievedUser);

        //make sure the user was deleted
        retrievedUser = userDao.getUserByUserNameSynchronous(username);
        assertThat(retrievedUser, equalTo(null));
    }

    @Test
    public void updateUserTest() throws Exception {
        User user = new User(username, password);
        User retrievedUser;

        //make sure the user doesn't exist first
        retrievedUser = userDao.getUserByUserNameSynchronous(username);
        assertThat(retrievedUser, equalTo(null));

        //add the user to the database
        userDao.insert(user);

        //get the user by the name
        retrievedUser = userDao.getUserByUserNameSynchronous(username);

        //make sure it matches
        assertThat(retrievedUser.getUsername(), equalTo(user.getUsername()));

        //give it a new username and update the entry
        String newUsername = "unitTestUserUpdate";
        retrievedUser.setUsername(newUsername);
        userDao.update(retrievedUser);

        //get the updated user form the database and check  if the username updated
        User updatedUser = userDao.getUserByUserNameSynchronous(newUsername);
        assertThat(updatedUser.getUsername(), equalTo(newUsername));

        //make sure the old username is gone
        assertThat(userDao.getUserByUserNameSynchronous(username), equalTo(null));
    }
}
