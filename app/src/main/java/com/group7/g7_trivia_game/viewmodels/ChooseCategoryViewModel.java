
/**
 * View model for ChooseCategoryActivity.
 * @author Cristina Pizano
 * @since 8/8/2025
 */
package com.group7.g7_trivia_game.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class ChooseCategoryViewModel extends ViewModel {
    private final MutableLiveData<List<String>> categories = new MutableLiveData<>();

    public ChooseCategoryViewModel() {
        categories.setValue(Arrays.asList("Math", "Geography", "Literature", "Science", "History"));
    }

    public LiveData<List<String>> getCategories() { return categories; }
}
