package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RandomExpectedDeckProvider extends ExpectedDeckProvider {

    public RandomExpectedDeckProvider(int amountsOfCards) {
        super(amountsOfCards);
    }

    @Override
    public List<Card> provide(Object context, boolean shuffle) {
        int amountsOfCards = (Integer)context;
        List<Card> cards = new ArrayList<>(amountsOfCards * 2);
        Stream.generate(() -> amountsOfCards).limit(amountsOfCards).forEach((i) -> {
            Value value = Value.getRandom();
            Suit suit = Suit.getRandom();
            cards.add(new Card(value, suit));
        });
        return cards;
    }
}
