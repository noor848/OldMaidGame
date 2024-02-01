# OldMaidGame
Java Multithreading to simulate the old maid card game, in which each player is represented as a thread that plays the game.


The game can be played by two players or more using the
standard 52-card deck, plus one additional card, the Joker. The game starts by dividing the
cards equally between the players (it is okay if some players have one more card than the
others). The objective of the game is to discard all cards. Specifically, a player can discard a
“matching pair” of cards, which are two cards with the same value and the same color, i.e.,
Spades (♠) must match with Clubs (♣) and Diamonds (♦) must match with Hearts (♥).
Because the Joker has no match, the player who has the Joker at the end of the game will lose.
Assuming we have four players, at the beginning of the game and after the cards are dealt, all
players will throw all matching pairs in their hands. Then, the first player will pick one random
card from the fourth player and add it to his or her hand. If the card results in having a
matching pair, then the player may discard this pair. Similarly, the second player will take one
random card from the first player and discard any matching pair. Then, the third player will take
one random card from the second player and discard any matching pair. Finally, the fourth
player will take one random card from the third player and discard any matching pair. This circle
will continue till eventually all players have discarded their cards, except for the player who has
the Joker, who will be considered the loser of the game.
