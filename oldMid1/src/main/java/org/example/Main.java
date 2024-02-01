package org.example;

public class Main {
    public static void main(String[] args) {
        Game oldMaidGame = new OldMaidGame();
        oldMaidGame.intializeGame();
        oldMaidGame.startGame();
        oldMaidGame.reportResults();
    }
}