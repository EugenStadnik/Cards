package com.deckofcards.api.pojo;

import java.util.Random;

public enum Suit {

    HEARTS("H"), CLUBS("C"),
    DIAMONDS("D"), SPADES("S");

    private String shortName;

    private Suit(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Suit getRandom() {
        return Suit.values()[new Random().nextInt(Suit.values().length)];
    }

}
