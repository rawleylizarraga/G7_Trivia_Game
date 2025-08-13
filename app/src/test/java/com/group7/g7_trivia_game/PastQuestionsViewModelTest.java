
/**
 * Unit test for PastQuestionsViewModel.
 * @author Cristina Pizano
 * @since 8/2/2025
 */
package com.group7.g7_trivia_game;

import static org.junit.Assert.*;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.group7.g7_trivia_game.viewmodels.PastQuestionsViewModel;

import org.junit.Rule;
import org.junit.Test;
import java.util.List;

public class PastQuestionsViewModelTest {

    @Rule public InstantTaskExecutorRule instant = new InstantTaskExecutorRule();

    @Test
    public void testPastQuestionsLoaded() {
        PastQuestionsViewModel vm = new PastQuestionsViewModel();
        List<String> list = vm.getPastQuestions().getValue();
        assertNotNull(list);
        assertTrue(list.contains("What is 2+2?"));
    }
}
