package com.deckofcards.api.utils.factories;

import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;
import com.deckofcards.api.utils.providers.expected_deck_providers.*;

public class ExpectedCardsProviderFactory implements ProviderFactory {

    private final String numberOfCards;
    private final Integer numberOfDecks;

    public ExpectedCardsProviderFactory(String numberOfCards, Integer numberOfDecks) {
        this.numberOfCards = numberOfCards;
        this.numberOfDecks = numberOfDecks;
    }

    @Override
    public ExpectedDeckProvider getProvider() {
        return getProvider(numberOfCards, numberOfDecks);
    }

    public ExpectedDeckProvider getProvider(String numberOfCards, Integer numberOfDecks) {
        if(PARAMETER_CHECKER.isAll(numberOfCards)) {
            return new FullExpectedDeckProvider(numberOfDecks);
        } else if(PARAMETER_CHECKER.isNumber(numberOfCards)) {
            return new RandomExpectedDeckProvider(Integer.parseInt(numberOfCards));
        } else if(PARAMETER_CHECKER.isValue(numberOfCards)) {
            return new ValueExpectedDeckProvider(Value.getValueOf(numberOfCards));
        } else if(PARAMETER_CHECKER.isSuit(numberOfCards)) {
            return new SuitExpectedDeckProvider(Suit.valueOf(numberOfCards));
        } else {
            throw new IllegalArgumentException("The \""
                    + numberOfCards + "\" test parameter is not a card name or suit or valid cards quantity");
        }
    }
}
