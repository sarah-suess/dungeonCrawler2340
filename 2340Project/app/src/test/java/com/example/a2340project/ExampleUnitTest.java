package com.example.a2340project;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.a2340project.Models.LeaderBoard;
import com.example.a2340project.Models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
/*
    @Test
    public void testScoreDecreasesEvery10Seconds() {
        GameScreen gameScreen = new GameScreen();
        Player player = Player.getInstance();
        //gameScreen.countDownTimer.onTick(10000); // Simulate 10 seconds passing
        assertEquals(9, player.getScore());
    }
 */
    @Test
    public void testScoreDoesNotGoBelowZero() {
        Player player = Player.getInstance();
        player.setScore(-1);
        assertEquals(0, player.getScore());
    }

    @Test(expected = NullPointerException.class)
    public void testNullPlayerNameDetection() {
        Player player = Player.getInstance();
        player.setPlayerName(null);
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyPlayerNameDetection() {
        Player player = Player.getInstance();
        player.setPlayerName("");
    }

    @Test(expected = NullPointerException.class)
    public void testWhiteSpacePlayerNameDetection() {
        Player player = Player.getInstance();
        player.setPlayerName(" ");
    }

    @Test
    public void testUpdateDescendingOrderOfScores() {
        LeaderBoard leaderBoard;
        leaderBoard = LeaderBoard.getInstance();
        // Clear the leaderboard before the test
        leaderBoard.getleaderboardEntries().clear();
        leaderBoard.addEntry("Arina", 50);
        leaderBoard.addEntry("Liane",30);
        leaderBoard.addEntry("Maeci", 70);
        leaderBoard.addEntry("Kelsey", 40);
        leaderBoard.sortEntries();
        List<LeaderBoard.LeaderboardEntry> entries = leaderBoard.getleaderboardEntries();
        for (int i = 0; i < entries.size() - 1; i++) {
            int currentScore = entries.get(i).getScore();
            int nextScore = entries.get(i + 1).getScore();
            assertTrue(currentScore >= nextScore);
        }
    }

    @Test
    public void addHigherSeventhEntry() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.getleaderboardEntries().clear();

        LeaderBoard.LeaderboardEntry arina = new LeaderBoard.LeaderboardEntry("Arina", 50, new Date());
        LeaderBoard.LeaderboardEntry liane = new LeaderBoard.LeaderboardEntry("Liane", 30, new Date());
        LeaderBoard.LeaderboardEntry maeci = new LeaderBoard.LeaderboardEntry("Maeci", 70, new Date());
        LeaderBoard.LeaderboardEntry kelsey = new LeaderBoard.LeaderboardEntry("Kelsey", 40, new Date());
        LeaderBoard.LeaderboardEntry sarah = new LeaderBoard.LeaderboardEntry("Sarah", 70, new Date());
        LeaderBoard.LeaderboardEntry priya = new LeaderBoard.LeaderboardEntry("Priya", 50, new Date());
        LeaderBoard.LeaderboardEntry kara = new LeaderBoard.LeaderboardEntry("Kara", 50, new Date());

        leaderBoard.addEntry(arina);
        leaderBoard.addEntry(liane);
        leaderBoard.addEntry(maeci);
        leaderBoard.addEntry(kelsey);
        leaderBoard.addEntry(sarah);
        leaderBoard.addEntry(priya);
        assertEquals(leaderBoard.getleaderboardEntries().size(), 6);
        leaderBoard.addEntry(kara);
        assertEquals(leaderBoard.getleaderboardEntries().size(), 6);

        List<LeaderBoard.LeaderboardEntry> entries = leaderBoard.getleaderboardEntries();
        List<LeaderBoard.LeaderboardEntry> correct = new ArrayList<LeaderBoard.LeaderboardEntry>(Arrays.asList(maeci, sarah, arina, priya, kara, kelsey));
        assertEquals(entries, correct);
    }

    @Test
    public void addMoreThanMaximumEntries() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.getleaderboardEntries().clear();

        LeaderBoard.LeaderboardEntry arina = new LeaderBoard.LeaderboardEntry("Arina", 50, new Date());
        LeaderBoard.LeaderboardEntry liane = new LeaderBoard.LeaderboardEntry("Liane", 30, new Date());
        LeaderBoard.LeaderboardEntry maeci = new LeaderBoard.LeaderboardEntry("Maeci", 70, new Date());
        LeaderBoard.LeaderboardEntry kelsey = new LeaderBoard.LeaderboardEntry("Kelsey", 40, new Date());
        LeaderBoard.LeaderboardEntry sarah = new LeaderBoard.LeaderboardEntry("Sarah", 70, new Date());
        LeaderBoard.LeaderboardEntry priya = new LeaderBoard.LeaderboardEntry("Priya", 50, new Date());
        LeaderBoard.LeaderboardEntry kara = new LeaderBoard.LeaderboardEntry("Kara", 20, new Date());

        leaderBoard.addEntry(arina);
        leaderBoard.addEntry(liane);
        leaderBoard.addEntry(maeci);
        leaderBoard.addEntry(kelsey);
        leaderBoard.addEntry(sarah);
        leaderBoard.addEntry(priya);
        assertEquals(leaderBoard.getleaderboardEntries().size(), 6);
        leaderBoard.addEntry(kara);
        assertEquals(leaderBoard.getleaderboardEntries().size(), 6);


        List<LeaderBoard.LeaderboardEntry> entries = leaderBoard.getleaderboardEntries();
        List<LeaderBoard.LeaderboardEntry> correct = new ArrayList<LeaderBoard.LeaderboardEntry>(Arrays.asList(maeci, sarah, arina, priya, kelsey, liane));
        assertEquals(entries, correct);
    }

    @Test
    public void recentEntryIsSeparateWithFullLeaderboard() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.getleaderboardEntries().clear();

        LeaderBoard.LeaderboardEntry arina = new LeaderBoard.LeaderboardEntry("Arina", 50, new Date());
        LeaderBoard.LeaderboardEntry liane = new LeaderBoard.LeaderboardEntry("Liane", 30, new Date());
        LeaderBoard.LeaderboardEntry maeci = new LeaderBoard.LeaderboardEntry("Maeci", 70, new Date());
        LeaderBoard.LeaderboardEntry kelsey = new LeaderBoard.LeaderboardEntry("Kelsey", 40, new Date());
        LeaderBoard.LeaderboardEntry sarah = new LeaderBoard.LeaderboardEntry("Sarah", 70, new Date());
        LeaderBoard.LeaderboardEntry priya = new LeaderBoard.LeaderboardEntry("Priya", 50, new Date());

        //If we already had a full leaderboard and the recent entry's score was lower than the existing lowest score, the name shouldn't be on the leaderboard but should still be the most recent entry
        leaderBoard.addEntry(arina);
        leaderBoard.addEntry(liane);
        leaderBoard.addEntry(maeci);
        leaderBoard.addEntry(kelsey);
        leaderBoard.addEntry(sarah);
        leaderBoard.addEntry(priya);
        leaderBoard.setRecentEntry("Kara", 20);
        assertEquals(leaderBoard.getRecentEntry().getPlayername(), "Kara");

    }

    @Test
    public void recentEntryIsSeparateWithNotFullLeaderboard() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.getleaderboardEntries().clear();

        LeaderBoard.LeaderboardEntry arina = new LeaderBoard.LeaderboardEntry("Arina", 50, new Date());
        LeaderBoard.LeaderboardEntry liane = new LeaderBoard.LeaderboardEntry("Liane", 30, new Date());
        LeaderBoard.LeaderboardEntry maeci = new LeaderBoard.LeaderboardEntry("Maeci", 70, new Date());
        LeaderBoard.LeaderboardEntry kelsey = new LeaderBoard.LeaderboardEntry("Kelsey", 40, new Date());
        LeaderBoard.LeaderboardEntry sarah = new LeaderBoard.LeaderboardEntry("Sarah", 70, new Date());

        leaderBoard.addEntry(arina);
        leaderBoard.addEntry(liane);
        leaderBoard.addEntry(maeci);
        leaderBoard.addEntry(kelsey);
        leaderBoard.addEntry(sarah);
        leaderBoard.setRecentEntry("Priya", 20);
        assertEquals(leaderBoard.getRecentEntry().getPlayername(), "Priya");
    }

    @Test(expected = NullPointerException.class)
    public void leaderboardEntryWithEmptyName() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.addEntry("", 100);
    }

    @Test(expected = NullPointerException.class)
    public void leaderboardEntryWithNullName() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.addEntry(null, 100);
    }

    @Test(expected = NullPointerException.class)
    public void leaderboardEntryWithOnlyWhitespaceName() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.addEntry("     ", 100);
    }

    @Test
    public void leaderboardEntryWithNegativeScore() {
        LeaderBoard leaderBoard = LeaderBoard.getInstance();
        leaderBoard.getleaderboardEntries().clear();
        leaderBoard.addEntry("Arina", -100);
        assertEquals(leaderBoard.getleaderboardEntries().get(0).getScore(), 0);
    }

}