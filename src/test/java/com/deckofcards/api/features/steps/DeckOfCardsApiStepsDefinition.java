package com.deckofcards.api.features.steps;

import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.factories.ActualDeckProviderFactory;
import com.deckofcards.api.utils.factories.ExpectedCardsProviderFactory;
import com.deckofcards.api.utils.providers.actual_deck_providers.ActualDeckProvider;
import com.deckofcards.api.utils.providers.expected_deck_providers.ExpectedDeckProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeckOfCardsApiStepsDefinition {

    private int numberOfDecks = 1;
    private Deck expectedDeck;
    private Deck actualDeck;
    private Deck drawnDeck;
    private Deck remainingDeck;

    @Given("the deck containing (.*) cards")
    public void theDeckContainingSomeCards(String numberOfCards) throws JsonProcessingException {
        ExpectedDeckProvider expectedDeckProvider = new ExpectedCardsProviderFactory(numberOfCards, numberOfDecks).getProvider();
        expectedDeck = expectedDeckProvider.provide(false);
        ActualDeckProvider actualDeckProvider = new ActualDeckProviderFactory(numberOfCards, numberOfDecks, expectedDeck).getProvider();
        actualDeck = actualDeckProvider.provide(false);
    }

    @When("(.*) cards are drawed from the (.*) of the deck")
    public void someCardsAreDrawedFromSomeSideOfTheDeck(String numberOfCards, String deckSide) {

    }

    @Then("appropriate quantity of cards remains in deck")
    public void appropriateQuantityOfCardsRemainsInDeck() {

    }

    @Then("the player have gotten only available cards from the deck")
    public void thePlayerHaveGottenOnlyAvailableCardsFromTheDeck() {

    }

    @Then("drawn cards are no longer in the deck")
    public void drawnCardsAreNoLongerInTheDeck() {

    }

}
