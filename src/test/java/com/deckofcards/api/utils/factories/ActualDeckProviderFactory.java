package com.deckofcards.api.utils.factories;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.providers.actual_deck_providers.ActualDeckProvider;
import com.deckofcards.api.utils.providers.actual_deck_providers.FullActualDeckProvider;
import com.deckofcards.api.utils.providers.actual_deck_providers.SpecificActualDeckProvider;

import java.util.List;

public class ActualDeckProviderFactory implements ProviderFactory {

    private String numberOfCards;
    private Integer numberOfDecks;
    private Deck expectedDeck;

    public ActualDeckProviderFactory(String numberOfCards, Integer numberOfDecks, Deck expectedDeck) {
        this.numberOfCards = numberOfCards;
        this.numberOfDecks = numberOfDecks;
        this.expectedDeck = expectedDeck;
    }

    @Override
    public ActualDeckProvider getProvider() {
        if(PARAMETER_CHECKER.isAll(numberOfCards)) {
            return new FullActualDeckProvider(numberOfDecks);
        } else if(PARAMETER_CHECKER.isNumber(numberOfCards)
                || PARAMETER_CHECKER.isValue(numberOfCards)
                || PARAMETER_CHECKER.isSuit(numberOfCards)) {
            return new SpecificActualDeckProvider(expectedDeck);
        } else {
            throw new IllegalArgumentException("The \""
                    + numberOfCards + "\" test parameter is not a card name or suit or valid cards quantity");
        }
    }

}
