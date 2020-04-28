Feature: Personal data page

  Scenario: Verifying input types
    Given I am logged in as "soldier"
    When I go to personal data page
    Then "firstName" input is "enabled" and is "not empty"
    And "lastName" input is "enabled" and is "not empty"
    And "rank" input is "disabled" and is "not empty"
    And "personalEvidenceNumber" input is "enabled" and is "not empty"
    And "birthDate" input is "enabled" and is "not empty"
    And "address" input is "enabled" and is "not empty"
    And Edit button is "enabled"

  Scenario: Validating inputs for editing personal data
    Given I am logged in as "soldier"
    And I go to personal data page
    When I enter "adsf" as "firstName"
    And I enter "zxczxc" as "lastName"
    And I enter "1111111111" as "personalEvidenceNumber"
    And I enter "21.12.1999" as "birthDate"
    And I select 4 value in address input
    And I click edit button
    Then I see alert with "Wystąpił problem z walidacją" title
    And Alert has "* Pole 'nazwisko' musi zaczynać się z dużej litery oraz nie może zawierać cyfr" text
    And Alert has "* Pole 'imię' musi zaczynać się z dużej litery oraz nie może zawierać cyfr" text
    And Alert has "* Pole 'pesel' musi zawierać dokładnie 11 cyfr" text

  Scenario: Editing personal data
    Given I am logged in as "soldier"
    And I go to personal data page
    When I enter "Czxasd" as "firstName"
    And I enter "Zxzcvxc" as "lastName"
    And I enter "11111111111" as "personalEvidenceNumber"
    And I enter "21.12.1999" as "birthDate"
    And I select 4 value in address input
    And I click edit button
    Then I see alert with "Wszystko przebiegło pomyślnie" title