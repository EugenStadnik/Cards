package com.deckofcards.api.features.steps;

import com.deckofcards.api.pojo.deck.Deck;
import com.deckofcards.api.features.drawers.CardsDrawer;
import com.deckofcards.api.features.factories.ActualDeckProviderFactory;
import com.deckofcards.api.features.factories.ExpectedDeckProviderFactory;
import com.deckofcards.api.features.providers.actual_deck_providers.ActualDeckProvider;
import com.deckofcards.api.features.providers.expected_deck_providers.ExpectedDeckProvider;
import io.cucumber.java8.En;
import org.json.JSONArray;
import org.junit.Assert;

import static com.deckofcards.api.utils.Constants.*;

public class DeckOfCardsApiStepsDefinition implements En {

    private final CardsDrawer cardsDrawer = new CardsDrawer();
    private ExpectedDeckProvider expectedDeckProvider;
    private ActualDeckProvider actualDeckProvider;
    private Deck fullExpectedSet;
    private Deck leftExpectedSet;
    private Deck actuallyDrawnSet;
    private Deck actuallyLeftSet;
    private int requestedAmount;

    public DeckOfCardsApiStepsDefinition() {
        /**
         * Explanation: Given the {int} sets of {string} {string} cards in the deck
         * The first parameter could be any valid positive number.
         * The second parameter could be:
         * - 'All' then full set of cards should be retrieved
         * - any valid card's values separated by coma then all suits of defined values cards will be retrieved
         * - any valid card's suits separated by coma then all values of defined suits cards will be retrieved
         * - any valid number then random defined amount of cards will be retrieved
         * - any valid card's values and suits - not implemented yet
         * The third parameter either will shuffle the cards or return them in a specific order
         * Example Given the 2 sets of 'ACE,DIAMONDS' 'SHUFFLED' cards in the deck
         * - will retrieve 2 shuffled sets of all ACES and DIAMONDS
         * */
        Given("the {int} sets of {string} {string} cards in the deck", (Integer amountOfSets, String cards, String shuffled) -> {
            expectedDeckProvider = new ExpectedDeckProviderFactory(cards, amountOfSets).getProvider();
            fullExpectedSet = expectedDeckProvider.provide(SHUFFLED_SCRIPT_PARAM.equals(shuffled.toUpperCase()));
            actualDeckProvider = new ActualDeckProviderFactory(cards, amountOfSets, fullExpectedSet).getProvider();
            Deck actualDeck = actualDeckProvider.provide(SHUFFLED_SCRIPT_PARAM.equals(shuffled.toUpperCase()));
            fullExpectedSet.setDeck_id(actualDeck.getDeck_id());
        });

        And("creative response status code is {int}", (Integer expectedStatusCode) -> {
            int actualStatusCode = actualDeckProvider.getCurrentResponse().statusCode();
            Assert.assertEquals("The expected and actual status codes for creative response don't match."
                            + "\nExpected status code: " + expectedStatusCode
                            + "\nActual status code: " + actualStatusCode
                    , (int)expectedStatusCode, actualStatusCode);
        });

        When("{string} cards are drawn from the {string} of the deck", (String amountOfCards, String deckSide) -> {
            actuallyDrawnSet = cardsDrawer.drawCards(fullExpectedSet, amountOfCards, deckSide);
            requestedAmount = cardsDrawer.getCurrentlyRequestedAmount();
            leftExpectedSet = expectedDeckProvider.updateDeck(fullExpectedSet, actuallyDrawnSet);
            actuallyLeftSet = cardsDrawer.drawCards(leftExpectedSet.getDeck_id(), leftExpectedSet.getRemaining(), deckSide);
        });

        Then("drawing response status code is {int}", (Integer expectedStatusCode) -> {
            int actualStatusCode = cardsDrawer.getCurrentResponse().statusCode();
            Assert.assertEquals("The expected and actual status codes for drawing response don't match."
                            + "\nExpected status code: " + expectedStatusCode
                            + "\nActual status code: " + actualStatusCode
                    , (int)expectedStatusCode, actualStatusCode);

        });

        And("requested amount match with drawn amount", () -> {
            Assert.assertEquals("The stickman retrieves not full amount of requested cards."
                            + "\nRequested amount: " + requestedAmount
                            + "\nRetrieved amount: " + actuallyDrawnSet.getCards().size()
                    , requestedAmount, actuallyDrawnSet.getCards().size());
        });

        Then("{int} cards remains in deck", (Integer remainingCards) -> {
            Assert.assertEquals("Actually remaining cards are not equals to expected."
                            + "\nExpected amount: " + remainingCards
                            + "\nActual amount: " + actuallyDrawnSet.getRemaining()
                    , remainingCards, actuallyDrawnSet.getRemaining());
        });

        Then("the player have gotten only available cards from the deck", () -> {
            Assert.assertTrue("The unavailable cards are drawn."
                            + "\nAvailable cards: " + new JSONArray(fullExpectedSet.getCards()).toString(3)
                            + "\nDrawn cards: " + new JSONArray(actuallyDrawnSet.getCards()).toString(3)
                    , fullExpectedSet.getCards().containsAll(actuallyDrawnSet.getCards()));
        });

        Then("drawn cards are no longer in the deck", () -> {
            if((actuallyLeftSet.getCards() == null && actuallyDrawnSet.getCards() == null)
                    || (actuallyLeftSet.getCards().isEmpty() && actuallyDrawnSet.getCards().isEmpty())) {
                Assert.assertFalse("Some drawn cards are left in the deck."
                        , actuallyLeftSet.getCards().size() > actuallyDrawnSet.getCards().size()
                                ? actuallyLeftSet.getCards().containsAll(actuallyDrawnSet.getCards())
                                : actuallyDrawnSet.getCards().containsAll(actuallyLeftSet.getCards()));
            }
        });
    }

}
