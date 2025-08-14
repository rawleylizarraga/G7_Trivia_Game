package com.group7.g7_trivia_game;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.group7.g7_trivia_game.database.QuestionDao;
import com.group7.g7_trivia_game.database.TriviaDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuestionDaoTest {
    private QuestionDao questionDao;
    private TriviaDatabase db;

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


}
