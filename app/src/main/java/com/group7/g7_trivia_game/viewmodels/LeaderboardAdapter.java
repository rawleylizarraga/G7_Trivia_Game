package com.group7.g7_trivia_game.viewmodels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group7.g7_trivia_game.R;
import com.group7.g7_trivia_game.database.entities.User;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {

    private List<User> userList;

    public LeaderboardAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void setUserList(List<User> newList) {
        this.userList = newList;
        notifyDataSetChanged(); // Refresh the RecyclerView
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_list_item, parent, false);
        return new LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        User user = userList.get(position);
        holder.usernameTextView.setText(user.getUsername());

        // Replace with actual score method
        int score = user.getScore(); // or user.getTotalPoints(), etc.
        holder.scoreTextView.setText(String.valueOf(score));
        holder.rankTextView.setText("#" + (position + 1));

    }

    @Override
    public int getItemCount() {
        return (userList == null) ? 0 : userList.size();
    }

    static class LeaderboardViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView scoreTextView;
        TextView rankTextView;

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            scoreTextView = itemView.findViewById(R.id.scoreTextView);
            rankTextView = itemView.findViewById(R.id.rankTextView); // initialize
        }
    }
}
