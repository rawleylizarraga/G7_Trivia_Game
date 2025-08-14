package com.group7.g7_trivia_game.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.group7.g7_trivia_game.database.entities.AnsweredQuestion;
import com.group7.g7_trivia_game.database.entities.Question;
import com.group7.g7_trivia_game.database.entities.User;
import com.group7.g7_trivia_game.database.typeconverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Database class containing {@link TriviaDatabase#getDatabase(Context)} and callback to populate.
 * Contains three tables: {@code User.class, Question.class, AnsweredQuestion.class}.
 *
 * @author Rawley Lizarraga
 * @since 7/31/2025
 */
@Database(entities = {User.class, Question.class, AnsweredQuestion.class}, version = 1, exportSchema = false)
@TypeConverters({LocalDateTypeConverter.class})
public abstract class TriviaDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract QuestionDao questionDao();

    public abstract AnsweredQuestionDao answeredQuestionDao();

    public static final String USER_TABLE = "user_table";
    public static final String QUESTION_TABLE = "question_table";
    public static final String ANSWERED_QUESTION_TABLE = "answered_question_table";
    private static final String DATABASE_NAME = "trivia_database";

    private static volatile TriviaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Returns a singleton TriviaDatabase ensuring that only one instance of the database is open at a time.
     * Creates the database the first time it is accessed using {@code sRoomDatabaseCallback}.
     * Runs asynchronously in the background using {@code ExecutorService}.
     * Use {@code LiveData<>} to access stored data.
     *
     * @param context Application context when called from repository.
     * @return Database as a singleton TriviaDatabase.
     */
    static TriviaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TriviaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TriviaDatabase.class, DATABASE_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Callback method used by {@link TriviaDatabase#getDatabase(Context)} to populate the initial database.
     */
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                UserDao newUserDao = INSTANCE.userDao();
                newUserDao.deleteAll();
                User admin = new User("admin1", "admin1");
                admin.setAdmin(true);
                newUserDao.insert(admin);

                User testUser1 = new User("testuser1", "testuser1");
                newUserDao.insert(testUser1);


                QuestionDao newQuestionDao = INSTANCE.questionDao();
                //newQuestionDao.deleteAll();
                Question math1 = new Question(0, 10, "4", "7", "3", "0", "Math", "What is 2 + 2?");
                newQuestionDao.insert(math1);
                Question math2 = new Question(0, 10, "10", "3434", "32", "9", "Math", "What is 5 x 2?");
                newQuestionDao.insert(math2);
                Question math3 = new Question(0, 10, "36", "23", "44", "11", "Math", "What is 72 / 2?");
                newQuestionDao.insert(math3);

                Question history1 = new Question(0, 10, "2025", "1970", "1500", "2077", "History", "What year was this app made?");
                newQuestionDao.insert(history1);
                Question history2 = new Question(0, 10, "George Washington", "Abraham Lincoln", "Julius Caesar", "Ramesses II", "History", "Who was the first president?");
                newQuestionDao.insert(history2);

            });
        }
    };
}
