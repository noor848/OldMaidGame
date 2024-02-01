package org.example;

import org.example.Card.Card;
import org.example.Player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    public static final int NUM_PLAYERS = 4;
    public static final int NUM_CARDS = 53;
    public static final List<Player> PLAYERS = new ArrayList<>();
    protected static final Object LOCK = new Object();
    public void intializeGame(){
        initializePlayers();
        dealCards();
    }

    public abstract void initializePlayers();

    protected abstract void dealCards();

    protected abstract List<Card> createDeck() ;

    protected abstract void startGame();

    protected abstract boolean isGameOver() ;

    protected abstract void reportResults();
}
