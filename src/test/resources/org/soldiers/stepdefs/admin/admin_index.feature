Feature: Admin index page

  Scenario: Verifying navigation bar
    Given I am logged in as "admin"
    Then I can see "Żołnierze" link
    And I can see "Użytkownicy" link
    And I can see "Adresy" link
    And I can see "Misje" link
    And I can see "Grupy" link
    And I can see "Aktualności" link
    And I can see "Przedmioty" link
    And I can see "PANEL ADMINISTRATORA" link
    And I can see "Aktualności" link
    And I can see "Ustawienia" link
    And I can see information about logged admin
    And I can see "Wyloguj" link