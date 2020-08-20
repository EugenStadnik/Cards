package com.deckofcards.api.utils.factories;

import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;
import com.deckofcards.api.utils.providers.expected_deck_providers.*;

public class ExpectedCardsProviderFactory implements ProviderFactory {

    private final String amountOfCards;
    private final int amountOfSets;

    public ExpectedCardsProviderFactory(String amountOfCards, Integer amountOfSets) {
        this.amountOfCards = amountOfCards;
        this.amountOfSets = amountOfSets;
    }

    @Override
    public ExpectedDeckProvider getProvider() {
        return getProvider(amountOfCards, amountOfSets);
    }

    public ExpectedDeckProvider getProvider(String amountOfCards, Integer amountOfSets) {
        if(PARAMETER_CHECKER.isAll(amountOfCards)) {
            return new FullExpectedDeckProvider(amountOfSets);
        } else if(PARAMETER_CHECKER.isNumber(amountOfCards)) {
            return new RandomExpectedDeckProvider(Integer.parseInt(amountOfCards));
        } else if(PARAMETER_CHECKER.isValue(amountOfCards)) {
            return new ValueExpectedDeckProvider(Value.valueOf(amountOfCards));
        } else if(PARAMETER_CHECKER.isSuit(amountOfCards)) {
            return new SuitExpectedDeckProvider(Suit.valueOf(amountOfCards));
        } else {
            throw new IllegalArgumentException("The \""
                    + amountOfCards + "\" test parameter is not a card name or suit or valid cards quantity");
        }
    }
}
