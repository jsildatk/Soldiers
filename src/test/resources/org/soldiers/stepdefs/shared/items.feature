Feature: Items page

  Scenario: Verifying main items page
    Given I am logged in as "soldier"
    When I go to items page
    Then I see equipment table
    And I have 1 item(s) in inventory
    And Equipment table contains "AK-47" item

  Scenario: All items page
    Given I am logged in as "soldier"
    And I go to items page
    When I go to all items page
    Then I see table with all items
    And I see 8 items
    And All items table contains "AK-47" item
    And All items table contains "Noktowizor" item
    And All items table contains "Dron" item
    And All items table contains "M4" item
    And All items table contains "Spadochron" item
    And All items table contains "C4" item
    And All items table contains "USP" item
    And All items table contains "M82A1" item

  Scenario: Item's details
    Given I am logged in as "soldier"
    And I go to items page
    And I go to all items page
    When I click "AK-47" item
    Then I see description about item
    And I see image of an item

  Scenario: Adding single item
    Given I am logged in as "soldier"
    And I go to items page
    When I click add button
    And I select "Dron" item
    And I click submit button
    Then I see alert with "Wszystko przebiegło pomyślnie" title
    And Alert has "Dodano przedmiot(y)" text
    And I see table with all items
    And I have 2 item(s) in inventory
    And Equipment table contains "AK-47" item
    And Equipment table contains "Dron" item

  Scenario: Removing added item
    Given I am logged in as "soldier"
    And I go to items page
    When I click remove button for "AK-47"
    Then I see alert with "Usunięto" title
    And Alert has "Usunięto przedmiot" text
    And I see table with all items
    And I have 1 item(s) in inventory
    And Equipment table does not contain "AK-47" item
    And Equipment table contains "Dron" item

  Scenario: Adding too much items
    Given I am logged in as "soldier"
    And I go to items page
    When I click add button
    And I select "AK-47" item
    And I select "M4" item
    And I select "Noktowizor" item
    And I select "Spadochron" item
    And I select "C4" item
    And I select "USP" item
    And I select "M82A1" item
    And I click submit button
    Then I see alert with "Problem" title
    And Alert has "Liczba przedmiotów po tej operacji przekorczy 7!" text
    And I see table with all items
    And I have 1 item(s) in inventory
    And Equipment table contains "Dron" item

  Scenario: Adding multiple items
    Given I am logged in as "soldier"
    And I go to items page
    When I click add button
    And I select "AK-47" item
    And I select "M4" item
    And I select "Noktowizor" item
    And I click submit button
    Then I see alert with "Wszystko przebiegło pomyślnie" title
    And Alert has "Dodano przedmiot(y)" text
    And I see table with all items
    And I have 4 item(s) in inventory
    And Equipment table contains "AK-47" item
    And Equipment table contains "Dron" item
    And Equipment table contains "Noktowizor" item
    And Equipment table contains "M4" item