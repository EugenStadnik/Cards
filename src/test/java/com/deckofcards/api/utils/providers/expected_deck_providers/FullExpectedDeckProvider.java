package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.deckofcards.Constants.*;

public class FullExpectedDeckProvider implements ExpectedDeckProvider {

    private int decksToProvide;

    public FullExpectedDeckProvider(int decksToProvide) {
        this.decksToProvide = decksToProvide;
    }

    @Override
    public Deck provide(boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>(decksToProvide * FULL_DECK_VOLUME * 2);
        Stream.generate(() -> decksToProvide).limit(decksToProvide).forEach((dec) -> {
            Stream.of(Value.values()).forEach((value) -> {
                Stream.of(Suit.values()).forEach((suit) -> {
                    Card card = new Card();
                    card.setValue(value);
                    card.setSuit(suit);
                    card.setCode(value.getShortName() + suit.getShortName());
                    cards.add(card);
                });
            });
        });
        if (shuffle) {
            Collections.shuffle(cards);
        }
        deck.setCards(cards);
        return deck;
    }

}
