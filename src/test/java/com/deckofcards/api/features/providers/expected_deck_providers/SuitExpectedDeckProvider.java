package com.deckofcards.api.features.providers.expected_deck_providers;

import com.deckofcards.api.features.factories.LoggerFactory;
import com.deckofcards.api.pojo.deck.Card;
import com.deckofcards.api.pojo.deck.Suit;
import com.deckofcards.api.pojo.deck.Value;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuitExpectedDeckProvider extends ExpectedDeckProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuitExpectedDeckProvider.class);

    public SuitExpectedDeckProvider(int amountOfSets, List<Suit> suitsToProvide) {
        super(amountOfSets, suitsToProvide);
    }

    @Override
    public List<Card> provide(int amountOfSets, Object context, boolean shuffle) {
        List<?> suitsToProvide = (List<?>)context;
        List<Card> cards = suitsToProvide.stream().flatMap((suitToProvide) -> {
            return Stream.of(Value.values()).map((value) -> {
                Card card;
                try {
                    card = new Card(value, (Suit)suitToProvide);
                } catch(MalformedURLException mue) {
                    LOGGER.error("Unable to create card:");
                    throw new RuntimeException(mue);
                }
                return card;
            });
        }).collect(Collectors.toList());
        return Stream.generate(() -> {return amountOfSets;}).limit(amountOfSets).map((integer) -> {
            return cards.stream();
        }).flatMap((cardStream) -> {
            return cardStream;
        }).collect(Collectors.toList());
    }

}
