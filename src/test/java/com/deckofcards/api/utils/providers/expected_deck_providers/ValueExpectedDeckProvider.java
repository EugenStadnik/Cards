package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ValueExpectedDeckProvider implements ExpectedDeckProvider {

    private Value value;

    public ValueExpectedDeckProvider(Value value) {
        this.value = value;
    }

    @Override
    public Deck provide(boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>(Suit.values().length * 2);
        Stream.of(Suit.values()).forEach((suit) -> {
            Card card = new Card();
            card.setValue(value);
            card.setSuit(suit);
            card.setCode(value.getShortName() + suit.getShortName());
            cards.add(card);
        });
        if (shuffle) {
            Collections.shuffle(cards);
        }
        deck.setCards(cards);
        return deck;
    }

}
