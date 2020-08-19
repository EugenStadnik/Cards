package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class SuitExpectedDeckProvider implements ExpectedDeckProvider {

    private Suit suit;

    public SuitExpectedDeckProvider(Suit suit) {
        this.suit = suit;
    }

    @Override
    public Deck provide(boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>(Value.values().length * 2);
        deck.setCards(cards);
        Stream.of(Value.values()).forEach((value) -> {
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
