package com.example.a2340project;

import org.junit.Test;
import static org.junit.Assert.assertSame;
import com.example.a2340project.Models.LeaderBoard;

public class leaderSingletonTest {
    @Test
    public void testSingletonInstance() {
        // Create two instances of the leaderboard
        LeaderBoard leaderboard1 = LeaderBoard.getInstance();
        LeaderBoard leaderboard2 = LeaderBoard.getInstance();

        // Ensure that both instances are the same (i.e., they reference the same object)
        assertSame(leaderboard1, leaderboard2);
    }
}