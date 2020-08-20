Feature: Verification of deckofcards API

  This feature sends different requests to deckofcards API
  and verifies responses according to it's values validity.

  Scenario Outline: Check if card count in deck is correct after drawing X cards from it
    Given the 1 sets of 'ALL' 'SHUFFLED' cards in the deck
    When '<drawnAmmount>' cards are drawn from the 'top' of the deck
    Then appropriate quantity of cards remains in deck

    Examples:
      | drawnAmmount |
      | 1            |
      | 26           |
      | 52           |

  Scenario: Create a new deck containing only cards of specific names
  and validate that player can only get these cards from it
    Given the 1 sets of 'ACE' 'UNSHUFFLED' cards in the deck
    When 'ALL' cards are drawn from the 'top' of the deck
    Then the player have gotten only available cards from the deck

  Scenario: Draw 5 specific cards from a bottom of the deck
  and check that card amount changed and drawn cards are no longer in the deck
    Given the 1 sets of '11' 'SHUFFLED' cards in the deck
    When '5' cards are drawn from the 'bottom' of the deck
    Then the player have gotten only available cards from the deck
    And appropriate quantity of cards remains in deck
    And drawn cards are no longer in the deck
