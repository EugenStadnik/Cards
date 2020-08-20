package com.deckofcards.api.features.steps;

import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.pojo.Value;
import com.deckofcards.api.utils.drawers.CardsDrawer;
import com.deckofcards.api.utils.factories.ActualDeckProviderFactory;
import com.deckofcards.api.utils.factories.ExpectedCardsProviderFactory;
import com.deckofcards.api.utils.providers.actual_deck_providers.ActualDeckProvider;
import com.deckofcards.api.utils.providers.expected_deck_providers.ExpectedDeckProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.deckofcards.api.utils.Constants.*;

public class DeckOfCardsApiStepsDefinition {

    private ExpectedDeckProvider expectedDeckProvider;
    private ActualDeckProvider actualDeckProvider;
    private final CardsDrawer cardsDrawer = new CardsDrawer();
    private Deck fullExpectedSet;
    private Deck leftExpectedSet;
    private Deck actuallyDrawnSet;
    private Deck actuallyLeftSet;
    private int requestedAmount;

    @Test
    public void test1() throws JsonProcessingException {
        theDeckContainingSomeCards(1, "ACE", "SHUFFLED");
        someCardsAreDrawnFromSomeSideOfTheDeck("3", "bottom");
        drawingResponseStatusCodeIsValid(200);
        appropriateQuantityOfCardsRemainsInDeck();
        thePlayerHaveGottenOnlyAvailableCardsFromTheDeck();
        drawnCardsAreNoLongerInTheDeck();
    }

    @Given("the {int} sets of {string} {string} cards in the deck")
    public void theDeckContainingSomeCards(int numberOfDecks, String cards, String shuffled) throws JsonProcessingException {
        expectedDeckProvider = new ExpectedCardsProviderFactory(cards, numberOfDecks).getProvider();
        fullExpectedSet = expectedDeckProvider.provide(SHUFFLED_SCRIPT_PARAM.equals(shuffled.toUpperCase()));
        actualDeckProvider = new ActualDeckProviderFactory(cards, numberOfDecks, fullExpectedSet).getProvider();
        Deck actualDeck = actualDeckProvider.provide(SHUFFLED_SCRIPT_PARAM.equals(shuffled.toUpperCase()));
        fullExpectedSet.setDeck_id(actualDeck.getDeck_id());
    }

    @And("creative response status code is {int}")
    public void creativeResponseStatusCodeIsValid(int expectedStatusCode) {
        int actualStatusCode = actualDeckProvider.getCurrentResponse().statusCode();
        Assert.assertEquals("The expected and actual status codes for creative response don't match."
                        + "\nExpected status code: " + expectedStatusCode
                        + "\nActual status code: " + actualStatusCode
                , expectedStatusCode, actualStatusCode);

    }

    @When("{string} cards are drawn from the {string} of the deck")
    public void someCardsAreDrawnFromSomeSideOfTheDeck(String numberOfCards, String deckSide) throws JsonProcessingException {
        actuallyDrawnSet = cardsDrawer.drawCards(fullExpectedSet, numberOfCards, deckSide);
        requestedAmount = cardsDrawer.getCurrentlyRequestedAmount();
        leftExpectedSet = expectedDeckProvider.updateDeck(fullExpectedSet, actuallyDrawnSet);
        actuallyLeftSet = cardsDrawer.drawCards(leftExpectedSet.getDeck_id(), deckSide, leftExpectedSet.getRemaining());
    }

    @Then("drawing response status code is {int}")
    public void drawingResponseStatusCodeIsValid(int expectedStatusCode) {
        int actualStatusCode = cardsDrawer.getCurrentResponse().statusCode();
        Assert.assertEquals("The expected and actual status codes for drawing response don't match."
                        + "\nExpected status code: " + expectedStatusCode
                        + "\nActual status code: " + actualStatusCode
                , expectedStatusCode, actualStatusCode);

    }

    @And("requested amount match with drawn amount")
    public void requestedAndDrawnAmountsAreMatch() {
        Assert.assertEquals("The stickman retrieves not full amount of requested cards."
                        + "\nRequested amount: " + requestedAmount
                        + "\nRetrieved amount: " + actuallyDrawnSet.getCards().size()
                , requestedAmount, actuallyDrawnSet.getCards().size());
    }

    @Then("appropriate quantity of cards remains in deck")
    public void appropriateQuantityOfCardsRemainsInDeck() {
        Assert.assertEquals("Actually remaining cards are not equals to expected."
                + "\nExpected amount: " + leftExpectedSet.getRemaining()
                + "\nActual amount: " + actuallyDrawnSet.getRemaining()
                , leftExpectedSet.getRemaining(), actuallyDrawnSet.getRemaining());
    }

    @Then("the player have gotten only available cards from the deck")
    public void thePlayerHaveGottenOnlyAvailableCardsFromTheDeck() {
        Assert.assertTrue("The unavailable cards are drawn."
                        + "\nAvailable cards: " + new JSONArray(fullExpectedSet.getCards()).toString(3)
                        + "\nDrawn cards: " + new JSONArray(actuallyDrawnSet.getCards()).toString(3)
                , fullExpectedSet.getCards().containsAll(actuallyDrawnSet.getCards()));
    }

    @Then("drawn cards are no longer in the deck")
    public void drawnCardsAreNoLongerInTheDeck() {
        Assert.assertFalse("Some drawn cards are left in the deck."
        , actuallyLeftSet.getCards().size() > actuallyDrawnSet.getCards().size()
                ? actuallyLeftSet.getCards().containsAll(actuallyDrawnSet.getCards())
                : actuallyDrawnSet.getCards().containsAll(actuallyLeftSet.getCards()));
    }

}
