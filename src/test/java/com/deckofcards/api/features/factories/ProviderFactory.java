package com.deckofcards.api.features.factories;

import com.deckofcards.api.utils.checkers.ParameterChecker;
import com.deckofcards.api.features.providers.DeckProvider;

public interface ProviderFactory {

    ParameterChecker PARAMETER_CHECKER = ParameterChecker.getInstance();

    DeckProvider getProvider();

}
