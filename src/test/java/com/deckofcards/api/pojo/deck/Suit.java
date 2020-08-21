package com.deckofcards.api.pojo.deck;

import com.deckofcards.api.utils.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Suit> valuesOf(String values) {
        return Arrays.stream(values.split(Constants.SPECIFIC_CARDS_DELIMETER)).map(Suit::valueOf)
                .collect(Collectors.toList());
    }

}
