package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.factories.LoggerFactory;
import com.deckofcards.api.utils.providers.DeckProvider;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public abstract class ExpectedDeckProvider implements DeckProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpectedDeckProvider.class);
    private final int amountOfSets;
    private final Object context;

    public ExpectedDeckProvider(int amountOfSets, Object context) {
        this.amountOfSets = amountOfSets;
        this.context = context;
    }

    @Override
    public Deck provide(boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = provide(amountOfSets, context, shuffle);
        deck.setCards(cards);
        Deck completedDeck = completeDeck(shuffle, cards, deck);
        LOGGER.info("The expected data set:\n" + new JSONObject(completedDeck).toString(3));
        return completedDeck;
    }

    public abstract List<Card> provide(int amountOfSets, Object context, boolean shuffle);

    public Deck completeDeck(boolean shuffle, List<Card> cards, Deck seed) {
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

    public Deck updateDeck(Deck previousDeckState, Deck drawnDeck) {
        if(drawnDeck == null) {
            return new Deck(previousDeckState);
        }
        Deck updatedDeck = new Deck(previousDeckState);
        updatedDeck.getCards().removeAll(drawnDeck.getCards());
        updatedDeck.setRemaining(updatedDeck.getCards().size());
        return updatedDeck;
    }

}
