package com.deckofcards.api.utils.drawers;

import com.deckofcards.api.helpers.ObjectMapperHelper;
import com.deckofcards.api.helpers.RestHelper;
import com.deckofcards.api.pojo.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.deckofcards.api.utils.Constants.*;

public class CardsDrawer {

    private final RestHelper restHelper = RestHelper.getInstance();
    private final ObjectMapperHelper objectMapperHelper = ObjectMapperHelper.getInstance();

    public Deck drawCards(String deckId, String side, int cardsToDraw) throws JsonProcessingException {
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE_URL)
                .setBasePath(deckId + DRAW_PATH + (BOTTOM_SCRIPT_PARAM.equals(side.toLowerCase()) ? BOTTOM_PATH : EMPTY_STRING))
                .addQueryParam(CARDS_COUNT_PARAM, cardsToDraw)
                .build();
        Response response = restHelper.sendGETRequest(spec);
        return objectMapperHelper.mapDeck(response.asString());
    }
}
