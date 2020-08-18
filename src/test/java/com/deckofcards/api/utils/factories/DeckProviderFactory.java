package com.deckofcards.api.utils.factories;

import com.deckofcards.Constants;
import com.deckofcards.api.utils.providers.deck_providers.DeckProvider;
import com.deckofcards.api.utils.providers.deck_providers.FullDeckProvider;
import com.deckofcards.api.utils.providers.deck_providers.RandomDeckProvider;
import com.deckofcards.api.utils.providers.deck_providers.ValueDeckProvider;

import com.deckofcards.api.pojo.Value;

public class DeckProviderFactory implements ProviderFactory {

    @Override
    public DeckProvider getProvider(String parameter) {
        if(isAll(parameter)) {
            return new FullDeckProvider();
        } else if(isValue(parameter)) {
            return new ValueDeckProvider(Value.getValueOf(parameter));
        } else if(isNumber(parameter)) {
            return new RandomDeckProvider(Integer.parseInt(parameter));
        } else {
            throw new IllegalArgumentException("The \""
                    + parameter + "\" test parameter is not a card name or suit or valid cards quantity");
        }
    }

    private boolean isNumber(String parameter) {
        try {
            Integer quantity = Integer.parseInt(parameter);
            if(quantity <= Constants.FULL$DECK$VOLUME && quantity >= 1) {
                return true;
            } else {
                return false;
            }
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    private boolean isAll(String parameter) {
        return Constants.FULL$DECK$VOLUME.toString().equals(parameter)
                || Constants.ALL.equals(parameter.toUpperCase());
    }

    private boolean isValue(String parameter) {
        try {
            Value.getValueOf(parameter);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

}
