package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.providers.DeckProvider;

import java.util.Collections;
import java.util.List;

public interface ExpectedDeckProvider extends DeckProvider {

    Deck provide(boolean shuffle);

    default Deck completeDeck(boolean shuffle, List<Card> cards, Deck seed) {
        if (shuffle) {
            seed.setShuffled(true);
            Collections.shuffle(cards);
        } else {
            seed.setShuffled(false);
        }
        seed.setRemaining(cards.size());
        seed.setCards(cards);
        seed.setSuccess(true);
        return seed;
    }

    default Deck updateDeck(Deck previousDeckState, Deck drawnDeck) {
        Deck updatedDeck = new Deck(previousDeckState);
        updatedDeck.getCards().removeAll(drawnDeck.getCards());
        updatedDeck.setRemaining(updatedDeck.getCards().size());
        return updatedDeck;
    }

}
