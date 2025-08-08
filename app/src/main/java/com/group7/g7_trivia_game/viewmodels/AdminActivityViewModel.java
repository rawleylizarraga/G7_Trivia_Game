package com.group7.g7_trivia_game.viewmodels;


import android.app.Application;
import android.icu.util.ULocale;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.group7.g7_trivia_game.database.TriviaRepository;
import com.group7.g7_trivia_game.database.entities.Question;


/**
 * View model for the admin activity to access the repository.
 * @author Madison Nolen
 * @since 8/2/2025
 */


public class AdminActivityViewModel extends AndroidViewModel {


    private final TriviaRepository repository;


    public AdminActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new TriviaRepository(application);
    }
    public void createCategory(String categoryName) {
        // Create a placeholder Question so category gets stored
        Question placeholder = new Question(
                0, // questionId (Room can auto-generate if annotated with @PrimaryKey(autoGenerate = true))
                1, // points
                "Placeholder correct answer", // answerCorrect
                "Wrong answer 1",             // answerWrong1
                "Wrong answer 2",             // answerWrong2
                "Wrong answer 3",             // answerWrong3
                categoryName,                  // category
                "Placeholder question for category: " + categoryName // question
        );
        repository.insertQuestion(placeholder);
    }
}
