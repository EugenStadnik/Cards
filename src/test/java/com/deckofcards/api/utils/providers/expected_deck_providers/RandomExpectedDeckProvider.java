package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RandomExpectedDeckProvider implements ExpectedDeckProvider {

    private final int amountsOfCards;

    public RandomExpectedDeckProvider(int amountsOfCards) {
        this.amountsOfCards = amountsOfCards;
    }

    @Override
    public Deck provide(boolean shuffle) {
        return provide(amountsOfCards, shuffle);
    }

    public Deck provide(int amountsOfCards, boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>(amountsOfCards * 2);
        Stream.generate(() -> amountsOfCards).limit(amountsOfCards).forEach((i) -> {
            Value value = Value.getRandom();
            Suit suit = Suit.getRandom();
            cards.add(new Card(value, suit));
        });
        return completeDeck(shuffle, cards, deck);
    }
}
