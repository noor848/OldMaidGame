package org.example;

import org.example.Card.Card;
import org.example.Player.Player;
import org.example.utils.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OldMaidGame extends Game{
    public void initializePlayers() {
        for (int i = 1; i <= NUM_PLAYERS; i++) {
            PLAYERS.add(new Player(i, LOCK));
        }
    }
    public void dealCards() {
        List<Card> deck = createDeck();
        Collections.shuffle(deck);
        int cardsPerPlayer = NUM_CARDS / NUM_PLAYERS;

        int cardIndex = 0;

        for (Player player : PLAYERS) {
            for (int i = 0; i < cardsPerPlayer; i++) {
                if (cardIndex < deck.size()) {
                    Card card = deck.get(cardIndex);
                    player.receiveCard(card);
                    cardIndex++;
                }
            }
        }
    }

    public List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 10; i++) {
                deck.add(new Card(suit, String.valueOf(i)));
            }
            deck.add(new Card(suit, "J"));
            deck.add(new Card(suit, "Q"));
            deck.add(new Card(suit, "K"));
            deck.add(new Card(suit, "A"));
        }

        deck.add(new Card(null, "Joker"));

        return deck;
    }

    public void startGame() {
        for (Player player : PLAYERS) {
            player.start();
        }

        // Main game loop
        try {
            synchronized (LOCK) {
                while (!isGameOver()) {
                    LOCK.wait();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isGameOver() {
        int playersWithCards = 0;

        for (Player player : PLAYERS) {
            if (player.hand.size() == 1 && !player.hasJoker) {
                playersWithCards++;
            }
        }

        return playersWithCards <= 1;
    }

    public void reportResults() {
        for (Player player : PLAYERS) {
            System.out.println(player);
            if (player.hasJoker) {
                System.out.println("Player " + player.getPlayerNumber() + " has the Joker and loses!");
            }
        }
    }
}
