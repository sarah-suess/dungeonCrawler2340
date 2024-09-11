package com.example.a2340project.Models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.Comparator;


// leaderboard class
public class LeaderBoard {
    private static volatile LeaderBoard leaderBoard;
    private List<LeaderboardEntry> leaderboardEntries;

    private LeaderboardEntry recentEntry;

    // leaderboard constructor
    private LeaderBoard() {
        leaderboardEntries = new ArrayList<>();
    }

    // provides access to single instance of LeaderBoard
    public static LeaderBoard getInstance() {
        if (leaderBoard == null) {
            synchronized (LeaderBoard.class) {
                if (leaderBoard == null) {
                    leaderBoard = new LeaderBoard();
                }
            }
        }
        return leaderBoard;
    }

    // add an entry to the leaderboard with playername and score
    public void addEntry(String playerName, int score) {
        LeaderboardEntry entry = new LeaderboardEntry(playerName, score, new Date());
        leaderboardEntries.add(entry);
        sortEntries();
        if (leaderboardEntries.size() > 6) {
            leaderboardEntries.remove(leaderboardEntries.size() - 1);
        }
    }

    // add an entry to the leaderboard with entry
    public void addEntry(LeaderboardEntry entry) {
        leaderboardEntries.add(entry);
        sortEntries();
        if (leaderboardEntries.size() > 6) {
            leaderboardEntries.remove(leaderboardEntries.size() - 1);
        }
    }

    // sort entry method
    public void sortEntries() {
        Collections.sort(leaderboardEntries, LeaderBoard.getInstance().getScoreComparator());
    }

    // set recent entry method
    public void setRecentEntry(String playerName, int score) {
        recentEntry = new LeaderboardEntry(playerName, score, new Date());
        addEntry(recentEntry);
    }

    // getter to get recent leaderboard entry
    public LeaderboardEntry getRecentEntry() {
        return recentEntry;
    }

    // return list of leaderboard entries
    public List<LeaderboardEntry> getleaderboardEntries() {
        return leaderboardEntries;
    }


    // comparator that sorts scores in descending order
    private Comparator<LeaderboardEntry> scoreComparator = new Comparator<LeaderboardEntry>() {
        @Override
        public int compare(LeaderboardEntry entry1, LeaderboardEntry entry2) {
            // Compare by score in descending order
            return Integer.compare(entry2.getScore(), entry1.getScore());
        }
    };

    public Comparator<LeaderboardEntry> getScoreComparator() {
        return scoreComparator;
    }

    // leaderboard entries class
    public static class LeaderboardEntry {
        private String playername;
        private int score;
        private Date dateTime;

        public LeaderboardEntry(String playerName, int score, Date dateTime) {
            if (playerName == null || playerName.isEmpty() || playerName.trim().isEmpty()) {
                throw new NullPointerException();
            } else {
                this.playername = playerName;
            }
            if (score < 0) {
                this.score = 0;
            } else {
                this.score = score;
            }
            this.dateTime = dateTime;
        }

        public String getPlayername() {
            return playername;
        }

        public int getScore() {
            return score;
        }

        public Date getDateTime() {
            return dateTime;
        }
    }
}
