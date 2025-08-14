
/**
 * Adapter for displaying trivia categories in a RecyclerView..
 * @author Cristina Pizano
 * @since 8/7/2025
 */
package com.group7.g7_trivia_game.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.group7.g7_trivia_game.R;
import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {

    public interface OnCategoryClick { void onClick(String category); }

    private final List<String> data = new ArrayList<>();
    private final OnCategoryClick click;

    public CategoryAdapter(OnCategoryClick click) { this.click = click; }

    public void submit(List<String> items) {
        data.clear();
        if (items != null) data.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
        View row = LayoutInflater.from(p.getContext()).inflate(R.layout.item_category, p, false);
        return new VH(row);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) { h.bind(data.get(pos), click); }
    @Override public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        private final TextView txt;
        VH(@NonNull View itemView) { super(itemView); txt = itemView.findViewById(R.id.txtCategory); }
        void bind(String category, OnCategoryClick click) {
            txt.setText(category);
            itemView.setOnClickListener(v -> click.onClick(category));
        }
    }
}
