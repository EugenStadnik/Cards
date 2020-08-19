package com.deckofcards.api.utils.providers.expected_deck_providers;

import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.providers.DeckProvider;

public interface ExpectedDeckProvider extends DeckProvider {

    Deck provide(boolean shuffle);

}
