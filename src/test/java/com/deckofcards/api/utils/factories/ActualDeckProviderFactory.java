package com.deckofcards.api.utils.factories;

import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.providers.actual_deck_providers.ActualDeckProvider;
import com.deckofcards.api.utils.providers.actual_deck_providers.FullActualDeckProvider;
import com.deckofcards.api.utils.providers.actual_deck_providers.SpecificActualDeckProvider;

public class ActualDeckProviderFactory implements ProviderFactory {

    private final String amountOfCards;
    private final int amountOfSets;
    private final Deck expectedDeck;

    public ActualDeckProviderFactory(String amountOfCards, Integer amountOfSets, Deck expectedDeck) {
        this.amountOfCards = amountOfCards;
        this.amountOfSets = amountOfSets;
        this.expectedDeck = expectedDeck;
    }

    @Override
    public ActualDeckProvider getProvider() {
        return getProvider(amountOfCards, amountOfSets, expectedDeck);
    }

    public ActualDeckProvider getProvider(String amountOfCards, Integer amountOfSets, Deck expectedDeck) {
        if(PARAMETER_CHECKER.isAll(amountOfCards)) {
            return new FullActualDeckProvider(amountOfSets);
        } else if(PARAMETER_CHECKER.isNumber(amountOfCards)
                || PARAMETER_CHECKER.isValue(amountOfCards)
                || PARAMETER_CHECKER.isSuit(amountOfCards)) {
            return new SpecificActualDeckProvider(expectedDeck);
        } else {
            throw new IllegalArgumentException("The \""
                    + amountOfCards + "\" test parameter is not a card name or suit or valid cards quantity");
        }
    }

}
