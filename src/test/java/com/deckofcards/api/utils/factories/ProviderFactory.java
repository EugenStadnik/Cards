package com.deckofcards.api.utils.factories;

import com.deckofcards.api.utils.checkers.ParameterChecker;
import com.deckofcards.api.utils.providers.DeckProvider;

public interface ProviderFactory {

    ParameterChecker PARAMETER_CHECKER = ParameterChecker.getInstance();

    DeckProvider getProvider();

}
