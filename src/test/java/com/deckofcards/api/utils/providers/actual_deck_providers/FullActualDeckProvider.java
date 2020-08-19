package com.deckofcards.api.utils.providers.actual_deck_providers;

import com.deckofcards.api.pojo.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import static com.deckofcards.Constants.*;

public class FullActualDeckProvider implements ActualDeckProvider {

    private Integer numberToProvide;

    public FullActualDeckProvider(Integer numberToProvide) {
        this.numberToProvide = numberToProvide;
    }

    @Override
    @SneakyThrows
    public Deck provide(boolean shuffle) throws JsonProcessingException {
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE_URL)
                .setBasePath(CREATE_PATH + (shuffle ? SHUFFLE_PATH : EMPTY_STRING))
                .addParam(DECKS_COUNT_PARAM, numberToProvide)
                .build();
        Response response = REST_HELPER.sendGETRequest(spec);
        return OBJECT_MAPPER.readValue(response.asString(), Deck.class);
    }
}
