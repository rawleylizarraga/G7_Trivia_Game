package com.group7.g7_trivia_game;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.group7.g7_trivia_game.databinding.ActivityAdminBinding;
import com.group7.g7_trivia_game.viewmodels.AdminActivityViewModel;

/**
 * Activity for the admin to create categories and questions.
 * This activity allows the admin to create new categories and questions for the trivia game.
 * It includes buttons for creating categories and questions, and a button to return to the welcome menu.
 * @author Madison Nolen
 * @since 8/2/2025
 */
public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
    private AdminActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AdminActivityViewModel.class);

        binding.createCategoryButton.setOnClickListener(v -> {
            String category = binding.categoryInputEditText.getText().toString().trim();

            if (category.isEmpty()) {
                Toast.makeText(this, "Please enter a category", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.createCategory(category);
            Toast.makeText(this, "Category added: " + category, Toast.LENGTH_SHORT).show();
            binding.categoryInputEditText.setText(""); // Clear input
        });

        binding.welcomeMenuButton.setOnClickListener(v -> {
            finish(); // returns to previous screen
        });
    }
}
