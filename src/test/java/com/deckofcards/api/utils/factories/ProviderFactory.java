package com.deckofcards.api.utils.factories;

import com.deckofcards.api.utils.providers.Provider;

public interface ProviderFactory {

    Provider getProvider(String parameter);

}
