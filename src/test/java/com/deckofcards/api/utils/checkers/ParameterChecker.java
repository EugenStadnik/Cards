package com.deckofcards.api.utils.checkers;

import com.deckofcards.api.utils.Constants;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParameterChecker {

    private static ParameterChecker instance;

    private ParameterChecker() {}

    public static ParameterChecker getInstance() {
        if(instance == null) {
            instance = new ParameterChecker();
        }
        return instance;
    }

    public boolean isNumber(String parameter) {
        try {
            int quantity = Integer.parseInt(parameter);
            return quantity >= 1;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    public boolean isAll(String parameter) {
        return Constants.ALL_SCRIPT_PARAM.equals(parameter.toUpperCase())
                || (isNumber(parameter) && Constants.FULL_DECK_VOLUME.equals(Integer.parseInt(parameter)));
    }

    public boolean isValues(String parameter) {
        try {
            Value.valuesOf(parameter);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

    public boolean isSuits(String parameter) {
        try {
            Suit.valuesOf(parameter);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

    public boolean isSuitsWithValues(String parameter) {
        List<Suit> suits = Arrays.stream(parameter.split(Constants.SPECIFIC_CARDS_DELIMETER)).map((s) -> {
            try {
                return Suit.valueOf(s);
            } catch (IllegalArgumentException iae) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        List<Value> values = Arrays.stream(parameter.split(Constants.SPECIFIC_CARDS_DELIMETER)).map((s) -> {
            try {
                return Value.getValueOf(s);
            } catch (IllegalArgumentException iae) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return !suits.isEmpty() && !values.isEmpty();
    }

}
