package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.deckofcards.api.utils.Constants.*;

public class FullExpectedDeckProvider implements ExpectedDeckProvider {

    private final int amountOfSets;

    public FullExpectedDeckProvider(int amountOfSets) {
        this.amountOfSets = amountOfSets;
    }

    @Override
    public Deck provide(boolean shuffle) {
        return provide(amountOfSets, shuffle);
    }

    public Deck provide(int amountOfSets, boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>(amountOfSets * FULL_DECK_VOLUME * 2);
        Stream.generate(() -> amountOfSets).limit(amountOfSets).forEach((dec) -> {
            Stream.of(Value.values()).forEach((value) -> {
                Stream.of(Suit.values()).forEach((suit) -> {
                    cards.add(new Card(value, suit));
                });
            });
        });
        return completeDeck(shuffle, cards, deck);
    }

}
