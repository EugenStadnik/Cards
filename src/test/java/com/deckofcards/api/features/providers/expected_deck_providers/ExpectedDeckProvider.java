package com.deckofcards.api.features.providers.expected_deck_providers;

import com.deckofcards.api.pojo.deck.Card;
import com.deckofcards.api.pojo.deck.Deck;
import com.deckofcards.api.features.factories.LoggerFactory;
import com.deckofcards.api.features.providers.DeckProvider;
import org.apache.log4j.Logger;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;

import static com.deckofcards.api.utils.JsonPretifier.*;

public abstract class ExpectedDeckProvider implements DeckProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpectedDeckProvider.class);
    private final int amountOfSets;
    private final Object context;

    public ExpectedDeckProvider(int amountOfSets, Object context) {
        if(amountOfSets < 0) {
            throw new InvalidParameterException("The " + amountOfSets + " provided amount of sets cannot be negative" +
                    ". It is unable to provide decks backward.");
        }
        this.amountOfSets = amountOfSets;
        this.context = context;
    }

    @Override
    public Deck provide(boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = provide(amountOfSets, context, shuffle);
        deck.setCards(cards);
        Deck completedDeck = completeDeck(shuffle, cards, deck);
        LOGGER.info("The expected data set:\n" + pretify(completedDeck));
        return completedDeck;
    }

    public abstract List<Card> provide(int amountOfSets, Object context, boolean shuffle);

    public Deck completeDeck(boolean shuffle, List<Card> cards, Deck seed) {
        if (shuffle) {
            Collections.shuffle(cards);
        }
        seed.setShuffled(shuffle);
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
        LOGGER.info("The residual expected deck:\n" + pretify(updatedDeck));
        return updatedDeck;
    }

}
