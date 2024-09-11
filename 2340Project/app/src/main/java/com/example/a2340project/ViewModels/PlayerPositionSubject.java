package com.example.a2340project.ViewModels;
import java.util.ArrayList;
import java.util.List;

/* PLAYER POSITION SUBJECT */
public class PlayerPositionSubject {
    private List<PlayerPositionObserver> observers = new ArrayList<>();

    public void addObserver(PlayerPositionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PlayerPositionObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(int x, int y) {
        for (PlayerPositionObserver observer : observers) {
            observer.onPlayerPositionChanged(x, y);
        }
    }
}
