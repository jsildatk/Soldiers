Feature: Soldier index page

  Scenario: Verifying navigation bar
    Given I am logged in as "soldier"
    Then "Dane osobowe" link is visible
    And "Misje" link is visible
    And "Wyposażenie" link is visible
    And "PANEL ŻOŁNIERZA" link is visible
    And "Aktualności" link is visible
    And "Ustawienia" link is visible
    And Information about logged user is visible
    And "Wyloguj" link is visible

  Scenario: Logging out from application
    Given I am logged in as "soldier"
    When I click "Wyloguj" link
    Then I am on index page

  Scenario: Showing news page from index
    Given I am logged in as "soldier"
    When I click "Wszystkie aktualności" link
    Then I am on " Aktualności " page
    And I see 2 news
    And I see "Przerwa techniczna" news
    And I see "Bal maskowy" news