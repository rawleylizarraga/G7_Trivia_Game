
/**
 * Activity to choose a trivia category..
 * @author Cristina Pizano
 * @since 8/7/2025
 */

package com.group7.g7_trivia_game;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.group7.g7_trivia_game.adapters.CategoryAdapter;
import com.group7.g7_trivia_game.viewmodels.ChooseCategoryViewModel;

public class ChooseCategoryActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "extra_category";

    private ChooseCategoryViewModel viewModel;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        RecyclerView rv = findViewById(R.id.rvCategories);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(category -> {
            Intent result = new Intent();
            result.putExtra(EXTRA_CATEGORY, category);
            setResult(RESULT_OK, result);
            finish();
        });
        rv.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        viewModel = new ViewModelProvider(this).get(ChooseCategoryViewModel.class);
        viewModel.getCategories().observe(this, adapter::submit);
    }
}
