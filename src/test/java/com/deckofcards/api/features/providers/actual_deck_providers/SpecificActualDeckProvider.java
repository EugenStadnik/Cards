package com.deckofcards.api.features.providers.actual_deck_providers;

import com.deckofcards.api.helpers.PropertiesHelper;
import com.deckofcards.api.pojo.deck.Card;
import com.deckofcards.api.pojo.deck.Deck;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.stream.Collectors;

import static com.deckofcards.api.utils.Constants.*;

public class SpecificActualDeckProvider implements ActualDeckProvider {

    private final PropertiesHelper propertiesHelper;
    private final Deck expectedDeck;
    private Response currentResponse;

    public SpecificActualDeckProvider(Deck expectedDeck) {
        propertiesHelper = PropertiesHelper.getInstance();
        this.expectedDeck = expectedDeck;
    }

    @Override
    public Deck provide(boolean shuffle) {
        return provide(expectedDeck, shuffle);
    }

    public Deck provide(Deck expectedDeck, boolean shuffle) {
        String cardsToProvide = expectedDeck.getCards().stream().map(Card::getCode)
                .collect(Collectors.joining(SPECIFIC_CARDS_DELIMITER));
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE_API_URL)
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
