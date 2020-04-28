Feature: Admin's addresses page

  Scenario: Displaying table with all addresses
    Given I am logged in as "admin"
    When I go to addresses page
    Then I see addresses table
    And Addresses table contains
      | id | street            | town        | postCode |
      | 1  | Wesola 5          | Sierakowice | 83-340   |
      | 2  | Marynarska 23/102 | Gdansk      | 80-126   |
      | 3  | Potocka 87        | Warszawa    | 21-340   |
      | 4  | Marusarzowny 18   | Gdansk      | 80-126   |
      | 5  | Sloneczna 10/20   | Kartuzy     | 84-210   |
      | 6  | Wita Stwosza 57   | Gdansk      | 80-298   |
      | 7  | Mickiewicza 1     | Lebork      | 58-963   |
      | 8  | Potockiego 18     | Warszawa    | 21-879   |
      | 9  | Zakapowa 98/89    | Koszalin    | 19-888   |
      | 10 | Lema 87           | Gdansk      | 80-126   |
      | 11 | Paracka 2/12      | Krakow      | 23-230   |
      | 12 | Weselska 8/23     | Sierakowice | 83-340   |
      | 13 | Zakopianska 11/11 | Koszalin    | 19-887   |
      | 14 | Dworcowa 21       | Sierakowice | 83-340   |
      | 15 | Rumunska 55       | Gdansk      | 80-121   |
      | 16 | Spacerowa 7       | Gdynia      | 89-111   |
      | 17 | Spacerowa 2/4     | Sierakowice | 83-340   |

  Scenario: Searching addresses by town
    Given I am logged in as "admin"
    And I go to addresses page
    When I type "Sierakowice" in search input
    And I click search button
    Then Addresses table contains
      | id | street        | town        | postCode |
      | 1  | Wesola 5      | Sierakowice | 83-340   |
      | 12 | Weselska 8/23 | Sierakowice | 83-340   |
      | 14 | Dworcowa 21   | Sierakowice | 83-340   |
      | 17 | Spacerowa 2/4 | Sierakowice | 83-340   |

  Scenario: Searching not existing addresses by town
    Given I am logged in as "admin"
    And I go to addresses page
    When I type "ASdf" in search input
    And I click search button
    Then I see alert with "Brak informacji" title
    And Alert has "Nie znaleziono adresu." text

  Scenario: Adding new address
    Given I am logged in as "admin"
    And I go to addresses page
    When I click add new address
    And I enter "Tesssdt 2" as "street"
    And I enter "Tesssdt" as "city"
    And I enter "23-231" as "postalCode"
    And I click add new address button
    Then I see alert with "Wszystko przebiegło pomyślnie" title
    And Alert has "Dodano adres" text

  Scenario: Editing address
    Given I am logged in as "admin"
    And I go to addresses page
    When I type "Lebork" in search input
    And I click search button
    And Addresses table contains
      | id | street        | town   | postCode |
      | 7  | Mickiewicza 1 | Lebork | 58-963   |
    Then I click button for edit
    And I enter "New 2" as "street"
    And I enter "Paczewo" as "city"
    And I enter "23-231" as "postalCode"
    And I click edit address button
    And I see alert with "Wszystko przebiegło pomyślnie" title
    And Alert has "Zedytowano adres" text

  Scenario: Removing address
    Given I am logged in as "admin"
    And I go to addresses page
    When I type "Paczewo" in search input
    And I click search button
    And Addresses table contains
      | id | street | town    | postCode |
      | 7  | New 2  | Paczewo | 23-231   |
    Then I click button for removal
    And I see alert with "Usunięto" title
    And Alert has "Usunięto adres o id: 7" text