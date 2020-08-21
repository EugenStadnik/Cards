package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.deckofcards.api.utils.Constants.*;

public class FullExpectedDeckProvider extends ExpectedDeckProvider {

    public FullExpectedDeckProvider(int amountOfSets, Object context) {
        super(amountOfSets, context);
    }

    @Override
    public List<Card> provide(int amountOfSets, Object context, boolean shuffle) {
        List<Card> cards = new ArrayList<>(amountOfSets * FULL_DECK_VOLUME * 2);
        Stream.generate(() -> amountOfSets).limit(amountOfSets).forEach((dec) -> {
            Stream.of(Value.values()).forEach((value) -> {
                Stream.of(Suit.values()).forEach((suit) -> {
                    cards.add(new Card(value, suit));
                });
            });
        });
        return cards;
    }

}
