package com.deckofcards.api.features.providers.actual_deck_providers;

import com.deckofcards.api.helpers.PropertiesHelper;
import com.deckofcards.api.pojo.deck.Deck;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.deckofcards.api.utils.Constants.*;

public class FullActualDeckProvider implements ActualDeckProvider {

    private final PropertiesHelper propertiesHelper;
    private final int amountOfSets;
    private Response currentResponse;

    public FullActualDeckProvider(int amountOfSets) {
        propertiesHelper = PropertiesHelper.getInstance();
        this.amountOfSets = amountOfSets;
    }

    @Override
    public Deck provide(boolean shuffle) {
        return provide(amountOfSets, shuffle);
    }

    public Deck provide(int amountOfSets, boolean shuffle) {
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(propertiesHelper.getProperty(BASE_URL_PROPERTY_NAME))
                .setBasePath(CREATE_PATH + (shuffle ? SHUFFLE_PATH : EMPTY_STRING))
                .addParam(DECKS_COUNT_PARAM, amountOfSets)
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
