package org.example.Card;

import org.example.utils.Suit;

abstract public class CardGame {
    public final Suit suit;
    public final String value;

    public CardGame(Suit suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
