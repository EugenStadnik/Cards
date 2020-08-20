package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SuitExpectedDeckProvider extends ExpectedDeckProvider {

    public SuitExpectedDeckProvider(Suit suitToProvide) {
        super(suitToProvide);
    }

    @Override
    public List<Card> provide(Object context, boolean shuffle) {
        Suit suitToProvide = (Suit)context;
        List<Card> cards = new ArrayList<>(Value.values().length * 2);
        Stream.of(Value.values()).forEach((value) -> {
            cards.add(new Card(value, suitToProvide));
        });
        return cards;
    }

}
