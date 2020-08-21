package com.deckofcards.api.features.providers;

import com.deckofcards.api.pojo.deck.Deck;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DeckProvider {

    Deck provide(boolean shuffle) throws JsonProcessingException;
}
