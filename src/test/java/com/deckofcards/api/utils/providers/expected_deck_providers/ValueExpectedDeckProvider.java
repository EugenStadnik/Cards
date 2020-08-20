package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ValueExpectedDeckProvider extends ExpectedDeckProvider {

    public ValueExpectedDeckProvider(Value valueToProvide) {
        super(valueToProvide);
    }

    public List<Card> provide(Object context, boolean shuffle) {
        Value valueToProvide = (Value)context;
        List<Card> cards = new ArrayList<>(Suit.values().length * 2);
        Stream.of(Suit.values()).forEach((suit) -> {
            cards.add(new Card(valueToProvide, suit));
        });
        return cards;
    }

}
