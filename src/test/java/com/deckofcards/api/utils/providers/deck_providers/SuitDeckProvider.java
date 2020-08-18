package com.deckofcards.api.utils.providers.deck_providers;

import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Suit;
import com.deckofcards.api.pojo.Value;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.deckofcards.Constants.*;

public class SuitDeckProvider extends DeckProvider {

    private Suit suit;

    private SuitDeckProvider() {
    }

    public SuitDeckProvider(Suit suit) {
        this.suit = suit;
    }

    @Override
    @SneakyThrows
    public Deck provide() {
        String cardsToProvade = Stream.of(Value.values()).map((value) -> {
            return value.getShortName() + suit.getShortName();
        }).collect(Collectors.joining(SPECIFIC$CARDS$DELIMETER));
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE$URL)
                .setBasePath(CREATE$SUBURL + SHUFFLE$SUBURL)
                .addPathParam(SPECIFIC$CARDS$PARAM, cardsToProvade)
                .build();
        Response response = getRestHelper().sendGETRequest(spec);
        return getObjectMapper().readValue(response.asString(), Deck.class);
    }
}
