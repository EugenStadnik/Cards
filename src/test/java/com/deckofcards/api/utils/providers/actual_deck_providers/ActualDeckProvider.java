package com.deckofcards.api.utils.providers.actual_deck_providers;

import com.deckofcards.api.helpers.ObjectMapperHelper;
import com.deckofcards.api.helpers.RestHelper;
import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.providers.DeckProvider;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ActualDeckProvider extends DeckProvider {

    ObjectMapperHelper OBJECT_MAPPER_HELPER = ObjectMapperHelper.getInstance();
    RestHelper REST_HELPER = RestHelper.getInstance();

    Deck provide(boolean shuffle) throws JsonProcessingException;

}
