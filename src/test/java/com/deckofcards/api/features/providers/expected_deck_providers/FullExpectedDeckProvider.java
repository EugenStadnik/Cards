package com.deckofcards.api.features.providers.expected_deck_providers;

import com.deckofcards.api.features.factories.LoggerFactory;
import com.deckofcards.api.pojo.deck.Card;
import com.deckofcards.api.pojo.deck.Suit;
import com.deckofcards.api.pojo.deck.Value;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.deckofcards.api.utils.Constants.*;

public class FullExpectedDeckProvider extends ExpectedDeckProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(FullExpectedDeckProvider.class);

    public FullExpectedDeckProvider(int amountOfSets, Object context) {
        super(amountOfSets, context);
    }

    @Override
    public List<Card> provide(int amountOfSets, Object context, boolean shuffle) {
        List<Card> cards = new ArrayList<>(amountOfSets * FULL_DECK_VOLUME * 2);
        Stream.generate(() -> amountOfSets).limit(amountOfSets).forEach((dec) -> {
            Stream.of(Value.values()).forEach((value) -> {
                Stream.of(Suit.values()).forEach((suit) -> {
                    Card card;
                    try {
                        card = new Card(value, suit);
                        cards.add(card);
                    } catch(MalformedURLException mue) {
                        LOGGER.error("Unable to create card:");
                        throw new RuntimeException(mue);
                    }
                });
            });
        });
        return cards;
    }

}
