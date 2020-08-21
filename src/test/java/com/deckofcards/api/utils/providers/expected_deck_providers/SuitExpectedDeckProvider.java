package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuitExpectedDeckProvider extends ExpectedDeckProvider {

    public SuitExpectedDeckProvider(int amountOfSets, List<Suit> suitsToProvide) {
        super(amountOfSets, suitsToProvide);
    }

    @Override
    public List<Card> provide(int amountOfSets, Object context, boolean shuffle) {
        List<?> suitsToProvide = (List<?>)context;
        List<Card> cards = suitsToProvide.stream().flatMap((suitToProvide) -> {
            return Stream.of(Value.values()).map((value) -> {
                return new Card(value, (Suit)suitToProvide);
            });
        }).collect(Collectors.toList());
        return Stream.generate(() -> {return amountOfSets;}).limit(amountOfSets).map((integer) -> {
            return cards.stream();
        }).flatMap((cardStream) -> {
            return cardStream;
        }).collect(Collectors.toList());
    }

}
