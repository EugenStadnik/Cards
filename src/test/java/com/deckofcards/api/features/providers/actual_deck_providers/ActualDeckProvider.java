package com.deckofcards.api.features.providers.actual_deck_providers;

import com.deckofcards.api.helpers.ObjectMapperHelper;
import com.deckofcards.api.helpers.RestHelper;
import com.deckofcards.api.pojo.deck.Deck;
import com.deckofcards.api.features.providers.DeckProvider;
import io.restassured.response.Response;

public interface ActualDeckProvider extends DeckProvider {

    ObjectMapperHelper OBJECT_MAPPER_HELPER = ObjectMapperHelper.getInstance();
    RestHelper REST_HELPER = RestHelper.getInstance();

    Deck provide(boolean shuffle);

    Response getCurrentResponse();

}
