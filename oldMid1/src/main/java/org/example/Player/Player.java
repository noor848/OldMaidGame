package org.example.Player;

import org.example.Card.Card;
import org.example.OldMaidGame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// Class representing a player in the game
public class Player extends Thread implements IGamePlayers {
    private final int playerNumber;
    public final List<Card> hand;
    public boolean hasJoker = false;
    private final Object lock;

    public Player(int playerNumber, Object lock) {
        this.playerNumber = playerNumber;
        this.hand = new ArrayList<>();
        this.lock = lock;
    }

    // Add a card to the player's hand
    public void receiveCard(Card card) {
        hand.add(card);
        if (card.getValue().equals("Joker")) {
            hasJoker = true;
        }
    }

    @Override
    public void run() {
        synchronized (lock) {
            lock.notify();
        }

        synchronized (lock) {
            try {
                lock.wait();
                playTurn(getPreviousPlayer());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void playTurn(Player previousPlayer) {
        synchronized (previousPlayer.lock) {
            while (previousPlayer.hand.isEmpty()) {
                try {
                    previousPlayer.lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            Card cardFromPreviousPlayer = previousPlayer.hand.remove(ThreadLocalRandom.current().nextInt(previousPlayer.hand.size()));

            hand.add(cardFromPreviousPlayer);

            System.out.println("Player " + playerNumber + " takes " + cardFromPreviousPlayer + " from Player " + previousPlayer.playerNumber);

            if (previousPlayer.hasJoker && previousPlayer.hand.size() == 1) {
                System.out.println("Player " + previousPlayer.playerNumber + " has the Joker and loses!");
                previousPlayer.hand.clear();
            }
            previousPlayer.discardMatchingPairs();
            discardMatchingPairs();
        }

        synchronized (lock) {
            lock.notify();
        }
    }

    public void discardMatchingPairs() {
        List<Card> pairsToDiscard = new ArrayList<>();

        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                Card card = hand.get(i);
                Card otherCard = hand.get(j);

                if (areMatchingPairs(card, otherCard)) {
                    pairsToDiscard.add(card);
                    pairsToDiscard.add(otherCard);
                }
            }
        }

        hand.removeAll(pairsToDiscard);
    }

    public boolean areMatchingPairs(Card card1, Card card2) {
        if (card1.getValue().equals("Joker") || card2.getValue().equals("Joker")) {
            return false;
        }
        if (card1.getSuit() == card2.getSuit()) {
            return true;
        }
        return (card1.getValue().equals(card2.getValue()));
    }

    public boolean hasDiscardedAllCards() {
        return hand.isEmpty();
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Player getPreviousPlayer() {
        int previousPlayerIndex = (playerNumber - 2 + OldMaidGame.NUM_PLAYERS) % OldMaidGame.NUM_PLAYERS;
        return OldMaidGame.PLAYERS.get(previousPlayerIndex);
    }

    @Override
    public String toString() {
        return "Player " + playerNumber + "'s hand (" + hand.size() + " cards): " + hand;
    }
}
