package org.example.Player;

import org.example.Card.Card;

public interface IGamePlayers {
     void receiveCard(Card card);
     void playTurn(Player previousPlayer) ;
     void discardMatchingPairs() ;
     boolean areMatchingPairs(Card card1, Card card2);
     boolean hasDiscardedAllCards();
     int getPlayerNumber();
     Player getPreviousPlayer() ;
}
