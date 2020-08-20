package com.deckofcards.api.pojo;

/**
 * This class is created to resolve transformation of enum
 * object to string with underscores and implement enumerator
 * because default implementation of {@code toString()} method
 * actually retrieves the name of enumerator variable
 * and override of this method doesn't resolve this issue
 * */
public final class Value {

    private static final Value _2 = new Value("2", "2");
    private static final Value _3 = new Value("3", "3");
    private static final Value _4 = new Value("4", "4");
    private static final Value _5 = new Value("5", "5");
    private static final Value _6 = new Value("6", "6");
    private static final Value _7 = new Value("7", "7");
    private static final Value _8 = new Value("8", "8");
    private static final Value _9 = new Value("9", "9");
    private static final Value _10 = new Value("0", "10");
    private static final Value JACK = new Value("J", "JACK");
    private static final Value QUEEN = new Value("Q", "QUEEN");
    private static final Value KING = new Value("K", "KING");
    private static final Value ACE = new Value("A", "ACE");
    private static final Value ENUM_$_VALUES[] = {_2, _3, _4, _5, _6, _7, _8, _9, _10, JACK, QUEEN, KING, ACE};
    private final String shortName;
    private final String representation;

    private Value(String shortName, String representation) {
        this.shortName = shortName;
        this.representation = representation;
    }

    public static Value[] values() {
        return ENUM_$_VALUES;
    }

    public static Value valueOf(String arg) {
        switch(arg) {
            case "2": return _2;
            case "3": return _3;
            case "4": return _4;
            case "5": return _5;
            case "6": return _6;
            case "7": return _7;
            case "8": return _8;
            case "9": return _9;
            case "10": return _10;
            case "JACK": return JACK;
            case "QUEEN": return QUEEN;
            case "KING": return KING;
            case "ACE": return ACE;
            default: throw new IllegalArgumentException("No enum constant " + Value.class + "." + arg);
        }
    }

    @Override
    public String toString() {
        return representation;
    }

    public String getShortName() {
        return shortName;
    }

    public static Value getRandom() {
        return Value.values()[(int)(Math.random() * Suit.values().length)];
    }
}