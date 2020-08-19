package com.deckofcards.api.utils.providers;

import com.deckofcards.api.pojo.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DeckProvider {

    Deck provide(boolean shuffle) throws JsonProcessingException;
}
