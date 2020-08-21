package com.deckofcards.api.features.drawers;

import com.deckofcards.api.helpers.ObjectMapperHelper;
import com.deckofcards.api.helpers.PropertiesHelper;
import com.deckofcards.api.helpers.RestHelper;
import com.deckofcards.api.pojo.deck.Deck;
import com.deckofcards.api.utils.checkers.ParameterChecker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.security.InvalidParameterException;

import static com.deckofcards.api.utils.Constants.*;

public class CardsDrawer {

    private final PropertiesHelper propertiesHelper;
    private final ParameterChecker parameterChecker = ParameterChecker.getInstance();
    private RestHelper restHelper;
    {
        try {
            restHelper = RestHelper.getInstance();
        } catch (Throwable throwable) {
            restHelper = null;
            throwable.printStackTrace();
        }
    }
    private final ObjectMapperHelper objectMapperHelper = ObjectMapperHelper.getInstance();
    private Response currentResponse;
    private Integer currentlyRequestedAmount;

    public CardsDrawer() {
        propertiesHelper = PropertiesHelper.getInstance();
    }

    public Deck drawCards(Deck generatedDeck, String requestedCards, String deckSide) {
        if(parameterChecker.isAll(requestedCards)) {
            currentlyRequestedAmount = generatedDeck.getRemaining();
            return drawCards(generatedDeck.getDeck_id(), currentlyRequestedAmount, deckSide);
        } else if(parameterChecker.isNumber(requestedCards)) {
            currentlyRequestedAmount = Integer.parseInt(requestedCards);
            return drawCards(generatedDeck.getDeck_id(), currentlyRequestedAmount, deckSide);
        } else {
            throw new IllegalArgumentException("The \""
                    + requestedCards + "\" test parameter is not 'ALL' or valid cards quantity");
        }
    }

    public Deck drawCards(String deckId, int amountToDraw, String side) {
        if(amountToDraw < 0) {
            throw new InvalidParameterException("The " + amountToDraw + " provided amount of sets cannot be negative" +
                    ". It is unable to draw cards backward.");
        }
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(propertiesHelper.getProperty(BASE_URL_PROPERTY_NAME))
                .setBasePath(deckId + DRAW_PATH + (BOTTOM_SCRIPT_PARAM.equals(side.toLowerCase()) ? BOTTOM_PATH : EMPTY_STRING))
                .addQueryParam(CARDS_COUNT_PARAM, amountToDraw)
                .build();
        currentResponse = restHelper.sendGETRequest(spec);
        return objectMapperHelper.mapDeck(currentResponse.asString());
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
