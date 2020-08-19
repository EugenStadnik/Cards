package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class RandomExpectedDeckProvider implements ExpectedDeckProvider {

    private int cardsToProvide;

    public RandomExpectedDeckProvider(int cardsToProvide) {
        this.cardsToProvide = cardsToProvide;
    }

    @Override
    public Deck provide(boolean shuffle) {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>(cardsToProvide * 2);
        Stream.generate(() -> cardsToProvide).limit(cardsToProvide).forEach((i) -> {
            Value value = Value.getRandom();
            Suit suit = Suit.getRandom();
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
