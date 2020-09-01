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

public class ValueExpectedDeckProvider extends ExpectedDeckProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValueExpectedDeckProvider.class);

    public ValueExpectedDeckProvider(int amountOfSets, List<Value> valuesToProvide) {
        super(amountOfSets, valuesToProvide);
    }

    public List<Card> provide(int amountOfSets, Object context, boolean shuffle) {
        List<?> valuesToProvide = (List<?>)context;
        List<Card> cards = valuesToProvide.stream().flatMap((valueToProvide) -> {
            return Stream.of(Suit.values()).map((suit) -> {
                Card card;
                try {
                    card = new Card((Value)valueToProvide, suit);
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
