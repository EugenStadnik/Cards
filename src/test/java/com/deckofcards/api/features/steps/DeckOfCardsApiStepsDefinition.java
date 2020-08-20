package com.deckofcards.api.features.steps;

import com.deckofcards.api.pojo.Deck;
import com.deckofcards.api.utils.checkers.ParameterChecker;
import com.deckofcards.api.utils.drawers.CardsDrawer;
import com.deckofcards.api.utils.factories.ActualDeckProviderFactory;
import com.deckofcards.api.utils.factories.ExpectedCardsProviderFactory;
import com.deckofcards.api.utils.providers.actual_deck_providers.ActualDeckProvider;
import com.deckofcards.api.utils.providers.expected_deck_providers.ExpectedDeckProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import static com.deckofcards.api.utils.Constants.*;

public class DeckOfCardsApiStepsDefinition {

    private ExpectedDeckProvider expectedDeckProvider;
    private final CardsDrawer cardsDrawer = new CardsDrawer();
    private final ParameterChecker parameterChecker = ParameterChecker.getInstance();
    private Deck fullExpectedSet;
    private Deck leftExpectedSet;
    private Deck actuallyDrawnSet;
    private Deck actuallyLeftSet;

    @Test
    public void test1() throws JsonProcessingException {
        theDeckContainingSomeCards(1, "ACE", "SHUFFLED");
        someCardsAreDrawnFromSomeSideOfTheDeck("2", "top");
        appropriateQuantityOfCardsRemainsInDeck();
        thePlayerHaveGottenOnlyAvailableCardsFromTheDeck();
        drawnCardsAreNoLongerInTheDeck();
    }

    @Given("the {int} sets of {string} {string} cards in the deck")
    public void theDeckContainingSomeCards(int numberOfDecks, String numberOfCards, String shuffled) throws JsonProcessingException {
        expectedDeckProvider = new ExpectedCardsProviderFactory(numberOfCards, numberOfDecks).getProvider();
        fullExpectedSet = expectedDeckProvider.provide(SHUFFLED_SCRIPT_PARAM.equals(shuffled.toUpperCase()));
        ActualDeckProvider actualDeckProvider = new ActualDeckProviderFactory(numberOfCards, numberOfDecks, fullExpectedSet).getProvider();
        Deck actualDeck = actualDeckProvider.provide(SHUFFLED_SCRIPT_PARAM.equals(shuffled.toUpperCase()));
        fullExpectedSet.setDeck_id(actualDeck.getDeck_id());
    }

    @When("{string} cards are drawn from the {string} of the deck")
    public void someCardsAreDrawnFromSomeSideOfTheDeck(String numberOfCards, String deckSide) throws JsonProcessingException {
        int requestedAmount;
        if(parameterChecker.isAll(numberOfCards)) {
            requestedAmount = fullExpectedSet.getRemaining();
            actuallyDrawnSet = cardsDrawer.drawCards(fullExpectedSet.getDeck_id(), deckSide, requestedAmount);
        } else if(parameterChecker.isNumber(numberOfCards)) {
            requestedAmount = Integer.parseInt(numberOfCards);
            actuallyDrawnSet = cardsDrawer.drawCards(fullExpectedSet.getDeck_id(), deckSide, requestedAmount);
        } else {
            throw new IllegalArgumentException("The \""
                    + numberOfCards + "\" test parameter is not 'ALL' or valid cards quantity");
        }
        leftExpectedSet = expectedDeckProvider.updateDeck(fullExpectedSet, actuallyDrawnSet);
        actuallyLeftSet = cardsDrawer.drawCards(leftExpectedSet.getDeck_id(), deckSide, leftExpectedSet.getRemaining());
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
