Feature: Verification of deckofcards API

  This feature sends different requests to deckofcards API
  and verifies responses according to it's values validity.

  Scenario Outline: Check if card count in deck is correct after drawing X cards from it
    Given the deck containing 'ALL' cards in 1 decks
    When <drawedAmmount> cards are drawed from the 'top' of the deck
    Then appropriate quantity of cards remains in deck

    Examples:
      | drawedAmmount |
      | 1             |
      | 26            |
      | 52            |

  Scenario: Create a new deck containing only cards of specific names
  and validate that player can only get these cards from it
    Given the deck containing 'ACE' cards in 1 decks
    When 'ALL' cards are drawed from the 'top' of the deck
    Then the player have gotten only available cards from the deck

  Scenario: Draw 5 specific cards from a bottom of the deck
  and check that card amount changed and drawn cards are no longer in the deck
    Given the deck containing '10' cards in 1 decks
    When '5' cards are drawed from the 'bottom' of the deck
    Then the player have gotten only available cards from the deck
    And appropriate quantity of cards remains in deck
    And drawn cards are no longer in the deck
