package com.deckofcards.api.pojo.deck;

import com.deckofcards.api.utils.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum Value {

    _2("2", "2"), _3("3", "3"), _4("4", "4"),
    _5("5", "5"), _6("6", "6"), _7("7", "7"),
    _8("8", "8"), _9("9", "9"), _10("0", "10"),
    JACK("J", "JACK"), QUEEN("Q", "QUEEN"),
    KING("K", "KING"), ACE("A", "ACE");

    private final String shortName;
    private final String representation;

    Value(String shortName, String representation) {
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

    public static List<Value> valuesOf(String values) {
        return Arrays.stream(values.split(Constants.SPECIFIC_CARDS_DELIMITER)).map(Value::getValueOf)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return representation;
    }

    public static Value getRandom() {
        return Value.values()[new Random().nextInt(Value.values().length)];
    }
}