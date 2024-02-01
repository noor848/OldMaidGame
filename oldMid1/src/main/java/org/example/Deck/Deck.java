package org.example.Deck;

import org.example.Card.Card;
import org.example.Card.CardGame;
import org.example.utils.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends GameDeck {
    public Deck() {
        this.cards = createDeck();
        shuffle();
    }
    protected List<CardGame> createDeck() {
        List<CardGame> deck = new ArrayList<>();
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
    protected void shuffle() {
        Collections.shuffle(cards);
    }
    protected CardGame dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        }
        return null; // or throw exception
    }
}
