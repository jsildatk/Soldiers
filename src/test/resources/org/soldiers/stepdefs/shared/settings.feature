Feature: User's settings

  Scenario: Verifying user's settings page
    Given I am logged in as "soldier"
    When I go to settings page
    Then "username" input is "enabled" and has proper value
    And "email" input is "enabled" and has proper value
    And "role" input is "disabled" and has proper value

  Scenario: Editing user's password
    Given I am logged in as "soldier"
    And I go to settings page
    When I click change password button
    And I enter "zaq1@WSX" as old password
    And I enter "zaq1@WSX" as new password
    Then I see alert with "Ustawiono nowe hasło" title
    And Alert has "Zostaniesz wylogowany" text
    And I click alert button
    And I am on index page