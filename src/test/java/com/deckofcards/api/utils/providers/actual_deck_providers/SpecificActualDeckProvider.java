package com.deckofcards.api.utils.providers.actual_deck_providers;

import com.deckofcards.api.pojo.Card;
import com.deckofcards.api.pojo.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

import static com.deckofcards.Constants.*;

public class SpecificActualDeckProvider implements ActualDeckProvider {

    private Deck expectedDeck;

    public SpecificActualDeckProvider(Deck expectedDeck) {
        this.expectedDeck = expectedDeck;
    }

    @Override
    @SneakyThrows
    public Deck provide(boolean shuffle) throws JsonProcessingException {
        String cardsToProvide = expectedDeck.getCards().stream().map((card) -> {
            return card.getCode();
        }).collect(Collectors.joining(SPECIFIC_CARDS_DELIMETER));
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE_URL)
                .setBasePath(CREATE_PATH + (shuffle ? SHUFFLE_PATH : EMPTY_STRING))
                .addPathParam(SPECIFIC_CARDS_PARAM, cardsToProvide)
                .build();
        Response response = REST_HELPER.sendGETRequest(spec);
        return OBJECT_MAPPER.readValue(response.asString(), Deck.class);
    }

}
