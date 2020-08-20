package com.deckofcards.api.utils.providers.actual_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.stream.Collectors;

import static com.deckofcards.api.utils.Constants.*;

public class SpecificActualDeckProvider implements ActualDeckProvider {

    private final Deck expectedDeck;
    private Response currentResponse;


    public SpecificActualDeckProvider(Deck expectedDeck) {
        this.expectedDeck = expectedDeck;
    }

    @Override
    public Deck provide(boolean shuffle) throws JsonProcessingException {
        return provide(expectedDeck, shuffle);
    }

    public Deck provide(Deck expectedDeck, boolean shuffle) throws JsonProcessingException {
        String cardsToProvide = expectedDeck.getCards().stream().map(Card::getCode)
                .collect(Collectors.joining(SPECIFIC_CARDS_DELIMETER));
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE_URL)
                .setBasePath(CREATE_PATH + (shuffle ? SHUFFLE_PATH : EMPTY_STRING))
                .addQueryParam(SPECIFIC_CARDS_PARAM, cardsToProvide)
                .build();
        currentResponse = REST_HELPER.sendGETRequest(spec);
        return OBJECT_MAPPER_HELPER.mapDeck(currentResponse.asString());
    }

    public Response getCurrentResponse() {
        if(currentResponse == null) {
            throw new IllegalStateException("There is no response for now. Send request first.");
        }
        return currentResponse;
    }

}
