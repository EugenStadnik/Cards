package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SuitExpectedDeckProvider implements ExpectedDeckProvider {

    private final Suit suitToProvide;

    public SuitExpectedDeckProvider(Suit suitToProvide) {
        this.suitToProvide = suitToProvide;
    }

    @Override
    public Deck provide(boolean shuffle) {
        return provide(suitToProvide, shuffle);
    }

    public Deck provide(Suit suitToProvide, boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>(Value.values().length * 2);
        deck.setCards(cards);
        Stream.of(Value.values()).forEach((value) -> {
            cards.add(new Card(value, suitToProvide));
        });
        return completeDeck(shuffle, cards, deck);
    }

}
