
/**
 * ChooseCategoryViewModelTest.java
 * @author Cristina Pizano
 * @since 8/8/2025
 */
package com.group7.g7_trivia_game;

import static org.junit.Assert.*;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.group7.g7_trivia_game.viewmodels.ChooseCategoryViewModel;

import org.junit.Rule;
import org.junit.Test;
import java.util.List;

public class ChooseCategoryViewModelTest {

    @Rule public InstantTaskExecutorRule instant = new InstantTaskExecutorRule();

    @Test
    public void testCategoriesLoaded() {
        ChooseCategoryViewModel vm = new ChooseCategoryViewModel();
        List<String> cats = vm.getCategories().getValue();
        assertNotNull(cats);
        assertTrue(cats.contains("Math"));
    }
}
