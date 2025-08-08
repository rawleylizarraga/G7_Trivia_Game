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
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return; // Avoid inserting empty categories
        }
        // "placeholder" question to hold the category
        Question categoryQuestion = new Question(
                0, // Auto-generate ID
                "", // Empty question text for now
                categoryName, // store the category
                "", "", "", "", // Empty answer fields
                ""  // Correct answer
        );
        repository.insertQuestion(categoryQuestion);
    }
}
