package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueExpectedDeckProvider extends ExpectedDeckProvider {

    public ValueExpectedDeckProvider(int amountOfSets, List<Value> valuesToProvide) {
        super(amountOfSets, valuesToProvide);
    }

    public List<Card> provide(int amountOfSets, Object context, boolean shuffle) {
        List<?> valuesToProvide = (List<?>)context;
        List<Card> cards = valuesToProvide.stream().flatMap((valueToProvide) -> {
            return Stream.of(Suit.values()).map((suit) -> {
                return new Card((Value)valueToProvide, suit);
            });
        }).collect(Collectors.toList());
        return Stream.generate(() -> {return amountOfSets;}).limit(amountOfSets).map((integer) -> {
            return cards.stream();
        }).flatMap((cardStream) -> {
            return cardStream;
        }).collect(Collectors.toList());
    }

}
