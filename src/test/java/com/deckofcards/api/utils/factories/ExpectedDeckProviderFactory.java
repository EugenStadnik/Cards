package com.deckofcards.api.utils.factories;

import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;
import com.deckofcards.api.utils.providers.expected_deck_providers.*;

public class ExpectedDeckProviderFactory implements ProviderFactory {

    private final String amountOfCards;
    private final int amountOfSets;

    public ExpectedDeckProviderFactory(String amountOfCards, int amountOfSets) {
        this.amountOfCards = amountOfCards;
        this.amountOfSets = amountOfSets;
    }

    @Override
    public ExpectedDeckProvider getProvider() {
        return getProvider(amountOfCards, amountOfSets);
    }

    public ExpectedDeckProvider getProvider(String cards, int amountOfSets) {
        if(PARAMETER_CHECKER.isAll(cards)) {
            return new FullExpectedDeckProvider(amountOfSets, null);
        } else if(PARAMETER_CHECKER.isNumber(cards)) {
            return new RandomExpectedDeckProvider(amountOfSets, Integer.parseInt(cards));
        } else if(PARAMETER_CHECKER.isValues(cards)) {
            return new ValueExpectedDeckProvider(amountOfSets, Value.valuesOf(cards));
        } else if(PARAMETER_CHECKER.isSuits(cards)) {
            return new SuitExpectedDeckProvider(amountOfSets, Suit.valuesOf(cards));
        } else {
            throw new IllegalArgumentException("The \""
                    + cards + "\" test parameter is not a card name or suit or valid cards amount");
        }
    }
}
