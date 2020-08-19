package com.deckofcards.api.utils.checkers;

import com.deckofcards.Constants;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

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
            Integer quantity = Integer.parseInt(parameter);
            if(quantity <= Constants.FULL_DECK_VOLUME && quantity >= 1) {
                return true;
            } else {
                return false;
            }
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    public boolean isAll(String parameter) {
        return Constants.FULL_DECK_VOLUME.toString().equals(parameter)
                || Constants.ALL.equals(parameter.toUpperCase());
    }

    public boolean isValue(String parameter) {
        try {
            Value.getValueOf(parameter);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

    public boolean isSuit(String parameter) {
        try {
            Suit.valueOf(parameter);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

}
