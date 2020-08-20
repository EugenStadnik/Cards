package com.deckofcards.api.utils.checkers;

import com.deckofcards.api.utils.Constants;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.Arrays;
import java.util.List;

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
            if(quantity >= 1) {
                return true;
            } else {
                return false;
            }
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    public boolean isAll(String parameter) {
        return Constants.ALL_SCRIPT_PARAM.equals(parameter.toUpperCase());
    }

    public boolean isValue(String parameter) {
        List<String> params = Arrays.asList(parameter.split(","));
        try {
            Value.valueOf(parameter.toUpperCase());
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

    public boolean isSuit(String parameter) {
        List<String> params = Arrays.asList(parameter.split(","));
        try {
            Suit.valueOf(parameter.toUpperCase());
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

}
