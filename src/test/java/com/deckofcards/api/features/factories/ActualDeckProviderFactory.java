package com.deckofcards.api.features.factories;

import com.deckofcards.api.pojo.deck.Deck;
import com.deckofcards.api.features.providers.actual_deck_providers.ActualDeckProvider;
import com.deckofcards.api.features.providers.actual_deck_providers.FullActualDeckProvider;
import com.deckofcards.api.features.providers.actual_deck_providers.SpecificActualDeckProvider;

public class ActualDeckProviderFactory implements ProviderFactory {

    private final String amountOfCards;
    private final int amountOfSets;
    private final Deck expectedDeck;

    public ActualDeckProviderFactory(String amountOfCards, int amountOfSets, Deck expectedDeck) {
        this.amountOfCards = amountOfCards;
        this.amountOfSets = amountOfSets;
        this.expectedDeck = expectedDeck;
    }

    @Override
    public ActualDeckProvider getProvider() {
        return getProvider(amountOfCards, amountOfSets, expectedDeck);
    }

    public ActualDeckProvider getProvider(String cards, int amountOfSets, Deck expectedDeck) {
        if(PARAMETER_CHECKER.isAll(cards)) {
            return new FullActualDeckProvider(amountOfSets);
        } else if(PARAMETER_CHECKER.isNumber(cards)
                || PARAMETER_CHECKER.isValues(cards)
                || PARAMETER_CHECKER.isSuits(cards)) {
            return new SpecificActualDeckProvider(expectedDeck);
        } else {
            throw new IllegalArgumentException("The \""
                    + cards + "\" test parameter is not a card name or suit or valid cards amount");
        }
    }

}
