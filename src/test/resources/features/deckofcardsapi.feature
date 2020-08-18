Feature: Verification of deckofcards API

  This feature sends different requests to deckofcards API
  and verifies responses according to it's values validity.

  Scenario Outline: Check if card count in deck is correct after drawing X cards from it
    Given the deck containing 'ALL' cards
    When <drawedAmmount> card(s) is(are) drawed from the 'top' of the deck
    Then appropriate quantity of cards remains in deck

    Examples:
      | drawedAmmount |
      | 1             |
      | 26            |
      | 52            |

  Scenario: Create a new deck containing only cards of specific names
  and validate that player can only get these cards from it
    Given the deck containing 'ACE' cards
    When 'ALL' card(s) is(are) drawed from the 'top' of the deck
    Then the player can only get 'ACE' cards from it

  Scenario: Draw 5 specific cards from a bottom of the deck
  and check that card amount changed and drawn
    Given the deck containing '10' cards
    When '5' card(s) is(are) drawed from the 'bottom' of the deck
    Then '5' card(s) is(are) drawn
    And appropriate quantity of cards remains in deck
