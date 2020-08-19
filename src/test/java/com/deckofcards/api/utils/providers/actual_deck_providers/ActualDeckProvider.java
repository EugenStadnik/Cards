package com.deckofcards.api.utils.providers.actual_deck_providers;

import com.deckofcards.api.helpers.RestHelper;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.providers.DeckProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface ActualDeckProvider extends DeckProvider {

    ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    RestHelper REST_HELPER = RestHelper.getInstance();

    Deck provide(boolean shuffle) throws JsonProcessingException;

}
