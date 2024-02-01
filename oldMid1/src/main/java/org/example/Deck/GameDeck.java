package org.example.Deck;

import org.example.Card.CardGame;

import java.util.List;

abstract class GameDeck {
    protected List<CardGame> cards;
    abstract protected List<CardGame> createDeck();
    abstract  protected void shuffle();
    abstract  protected CardGame dealCard();
}
