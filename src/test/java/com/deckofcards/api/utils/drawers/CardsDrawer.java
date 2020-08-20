package com.deckofcards.api.utils.drawers;

import com.deckofcards.api.helpers.ObjectMapperHelper;
import com.deckofcards.api.helpers.RestHelper;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.checkers.ParameterChecker;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.deckofcards.api.utils.Constants.*;

public class CardsDrawer {

    private final ParameterChecker parameterChecker = ParameterChecker.getInstance();
    private final RestHelper restHelper = RestHelper.getInstance();
    private final ObjectMapperHelper objectMapperHelper = ObjectMapperHelper.getInstance();
    private Response currentResponse;
    private Integer currentlyRequestedAmount;

    public Deck drawCards(Deck generatedDeck, String requestedCards, String deckSide) throws JsonProcessingException {
        if(parameterChecker.isAll(requestedCards)) {
            currentlyRequestedAmount = generatedDeck.getRemaining();
            return drawCards(generatedDeck.getDeck_id(), deckSide, currentlyRequestedAmount);
        } else if(parameterChecker.isNumber(requestedCards)) {
            currentlyRequestedAmount = Integer.parseInt(requestedCards);
            return drawCards(generatedDeck.getDeck_id(), deckSide, currentlyRequestedAmount);
        } else {
            throw new IllegalArgumentException("The \""
                    + requestedCards + "\" test parameter is not 'ALL' or valid cards quantity");
        }
    }

    public Deck drawCards(String deckId, String side, int amountToDraw) throws JsonProcessingException {
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE_URL)
                .setBasePath(deckId + DRAW_PATH + (BOTTOM_SCRIPT_PARAM.equals(side.toLowerCase()) ? BOTTOM_PATH : EMPTY_STRING))
                .addQueryParam(CARDS_COUNT_PARAM, amountToDraw)
                .build();
        currentResponse = restHelper.sendGETRequest(spec);
        Deck drawnSet;
        try {
            drawnSet = objectMapperHelper.mapDeck(currentResponse.asString());
        } catch(JsonParseException jpe) {
            drawnSet = null;
        }
        return drawnSet;
    }

    public Response getCurrentResponse() {
        if(currentResponse == null) {
            throw new IllegalStateException("There is no response for now. Send request first.");
        }
        return currentResponse;
    }

    public int getCurrentlyRequestedAmount() {
        if(currentlyRequestedAmount == null) {
            throw new IllegalStateException("There is no requested amount for now. Send request first to resolve it.");
        }
        return currentlyRequestedAmount;
    }
}
