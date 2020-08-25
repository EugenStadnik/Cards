package com.deckofcards.api.features.providers.expected_deck_providers;

import com.deckofcards.api.features.factories.LoggerFactory;
import com.deckofcards.api.pojo.deck.Card;
import com.deckofcards.api.pojo.deck.Suit;
import com.deckofcards.api.pojo.deck.Value;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomExpectedDeckProvider extends ExpectedDeckProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomExpectedDeckProvider.class);

    public RandomExpectedDeckProvider(int amountOfSets, int amountsOfCards) {
        super(amountOfSets, amountsOfCards);
    }

    @Override
    public List<Card> provide(int amountOfSets, Object context, boolean shuffle) {
        int amountsOfCards = (Integer)context;
        if(amountsOfCards < 0) {
            throw new InvalidParameterException("The " + amountsOfCards + " provided amount of cards cannot be negative" +
                    ". It is unable to provide cards backward.");
        }
        List<Card> cards = Stream.generate(() -> amountsOfCards).limit(amountsOfCards).map((i) -> {
            Value value = Value.getRandom();
            Suit suit = Suit.getRandom();
            Card card;
            try {
                card = new Card(value, suit);
            } catch(MalformedURLException mue) {
                LOGGER.error("Unable to create card:");
                throw new RuntimeException(mue);
            }
            return card;
        }).collect(Collectors.toList());
        return Stream.generate(() -> {return amountOfSets;}).limit(amountOfSets).map((integer) -> {
            return cards.stream();
        }).flatMap((cardStream) -> {
            return cardStream;
        }).collect(Collectors.toList());
    }
}
