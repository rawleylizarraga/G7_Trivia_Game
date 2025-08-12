/**
 *
 * @author Cristina Pizano
 * @since 7/31/2025
 */

package com.group7.g7_trivia_game.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class PastQuestionsViewModel extends ViewModel {

    // Backing property for the list of past questions
    private final MutableLiveData<List<String>> pastQuestions = new MutableLiveData<>();

    public PastQuestionsViewModel() {
        // Temporary sample data for testing and UI display
        pastQuestions.setValue(Arrays.asList(
                "What is 2+2?",
                "What is the capital of France?",
                "Who wrote 'To Kill a Mockingbird'?"
        ));
    }

    // Public LiveData so UI can observe
    public LiveData<List<String>> getPastQuestions() {
        return pastQuestions;
    }
}
