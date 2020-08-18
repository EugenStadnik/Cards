package com.deckofcards.api.utils.providers.deck_providers;

import com.deckofcards.api.helpers.RestHelper;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.providers.Provider;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class DeckProvider implements Provider {

    private ObjectMapper objectMapper = new ObjectMapper();
    private RestHelper restHelper = new RestHelper();

    public abstract Deck provide();

    public RestHelper getRestHelper() {
        return restHelper;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
