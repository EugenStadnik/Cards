package com.deckofcards.api.pojo;

import java.util.Random;

public enum Value {

    _2("2", "2"), _3("3", "3"), _4("4", "4"),
    _5("5", "5"), _6("6", "6"), _7("7", "7"),
    _8("8", "8"), _9("9", "9"), _10("0", "10"),
    JACK("J", "JACK"), QUEEN("Q", "QUEEN"),
    KING("K", "KING"), ACE("A", "ACE");

    private String shortName;
    private String representation;

    private Value(String shortName, String representation) {
        this.shortName = shortName;
        this.representation = representation;
    }

    public String getShortName() {
        return shortName;
    }

    public static Value getValueOf(String value) {
        switch(value) {
            case "2": return _2;
            case "3": return _3;
            case "4": return _4;
            case "5": return _5;
            case "6": return _6;
            case "7": return _7;
            case "8": return _8;
            case "9": return _9;
            case "10": return _10;
            default: return valueOf(value);
        }
    }

    @Override
    public String toString() {
        return representation;
    }

    public static Value getRandom() {
        return Value.values()[new Random().nextInt(Value.values().length)];
    }
}
