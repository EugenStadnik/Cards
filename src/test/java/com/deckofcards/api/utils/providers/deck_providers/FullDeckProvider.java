package com.deckofcards.api.utils.providers.deck_providers;

import com.deckofcards.api.pojo.Deck;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import static com.deckofcards.Constants.*;

public class FullDeckProvider extends DeckProvider {

    private Integer decksToProvide;

    public FullDeckProvider() {
    }

    @Override
    @SneakyThrows
    public Deck provide() {
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE$URL)
                .setBasePath(CREATE$SUBURL + SHUFFLE$SUBURL)
                .addPathParam(DECKS$COUNT$PARAM, decksToProvide)
                .build();
        Response response = getRestHelper().sendGETRequest(spec);
        return getObjectMapper().readValue(response.asString(), Deck.class);
    }
}
