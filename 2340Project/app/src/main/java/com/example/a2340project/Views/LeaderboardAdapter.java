package com.example.a2340project.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340project.Models.LeaderBoard;
import com.example.a2340project.R;

import java.util.List;

/* LEADERBOARD VIEW DATA ON GAME SCREEN */
public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {
    private List<LeaderBoard.LeaderboardEntry> entries;

    public LeaderboardAdapter(List<LeaderBoard.LeaderboardEntry> entries) {
        this.entries = entries;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_entry,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeaderBoard.LeaderboardEntry entry = entries.get(position);
        holder.playerNameTextView.setText(entry.getPlayername());
        holder.scoreTextView.setText("Score: " + entry.getScore());
        holder.dateTextView.setText("Date and Time: " + entry.getDateTime());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView playerNameTextView;
        private TextView scoreTextView;
        private TextView dateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            playerNameTextView = itemView.findViewById(R.id.player_name);
            scoreTextView = itemView.findViewById(R.id.score);
            dateTextView = itemView.findViewById(R.id.date_time_played);
        }
    }
}
