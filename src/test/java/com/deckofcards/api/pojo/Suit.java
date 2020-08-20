package com.deckofcards.api.pojo;

public enum Suit {

    HEARTS("H"), CLUBS("C"),
    DIAMONDS("D"), SPADES("S");

    private final String shortName;

    Suit(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Suit getRandom() {
        return Suit.values()[(int)(Math.random() * Suit.values().length)];
    }

}
