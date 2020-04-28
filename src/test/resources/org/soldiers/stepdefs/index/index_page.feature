Feature: Index page

  Scenario: Logging to application with invalid credentials
    Given I open index page
    When I enter "wsrgdfggh" as "username"
    And I enter "zzxc" as "password"
    And I click "Zaloguj" button
    Then Message "Nieprawidłowe dane logowania" is displayed in container with "class" "error"

  Scenario: Register validation with different passwords
    Given I open index page
    And I switch pane to register
    Then I select 1 value
    And I enter "asdfg" as "username1"
    And I enter "xdasf@asdf.pl" as "email1"
    And I enter "test" as "password1"
    And I enter "asf" as "confirmPassword"
    Then "Zarejestruj" button is "disabled"
    And Message "Hasła nie pasują" is displayed in container with "id" "passwordResponse"

  Scenario: Register validation with too short password
    Given I open index page
    And I switch pane to register
    Then I select 1 value
    And I enter "asdfg" as "username1"
    And I enter "xdasf@asdf.pl" as "email1"
    And I enter "test" as "password1"
    And I enter "test" as "confirmPassword"
    Then "Zarejestruj" button is "disabled"
    And Message "Za krótkie hasło" is displayed in container with "id" "passwordStrengthResponse"

  Scenario: Register validation with weak password
    Given I open index page
    And I switch pane to register
    Then I select 1 value
    And I enter "asdfg" as "username1"
    And I enter "xdasf@asdf.pl" as "email1"
    And I enter "aaaaaaaaaaaaa" as "password1"
    And I enter "aaaaaaaaaaaaa" as "confirmPassword"
    Then "Zarejestruj" button is "disabled"
    And Message "Słabe hasło" is displayed in container with "id" "passwordStrengthResponse"

  Scenario: Register validation with duplicated username
    Given I open index page
    And I switch pane to register
    Then I select 1 value
    And I enter "soldier_test" as "username1"
    And I enter "xdasf@asdf.pl" as "email1"
    And I enter "zaq1@WSX" as "password1"
    And I enter "zaq1@WSX" as "confirmPassword"
    Then "Zarejestruj" button is "disabled"
    And Message "Istnieje już taki użytkownik" is displayed in container with "id" "usernameResponse"

  Scenario: Register validation with duplicated email
    Given I open index page
    And I switch pane to register
    Then I select 1 value
    And I enter "sadf" as "username1"
    And I enter "soldier@wojsko.pl" as "email1"
    And I enter "zaq1@WSX" as "password1"
    And I enter "zaq1@WSX" as "confirmPassword"
    Then "Zarejestruj" button is "disabled"
    And Message "Istnieje już użytkownik o takim adresie email" is displayed in container with "id" "emailResponse"

  Scenario: Register validation with valid data
    Given I open index page
    And I switch pane to register
    Then I select 1 value
    And I enter "asdasdasd" as "username1"
    And I enter "adfgdfggsd@wp.pl" as "email1"
    And I enter "zaq1@WSX" as "password1"
    And I enter "zaq1@WSX" as "confirmPassword"
    Then "Zarejestruj" button is "enabled"
    And Message "Silne hasło" is displayed in container with "id" "passwordStrengthResponse"

  Scenario: Register an user
    Given I open index page
    And I switch pane to register
    Then I select 1 value
    And I enter "vvvvvvvf" as "username1"
    And I enter "vvvvv@wp.pl" as "email1"
    And I enter "zaq1@WSX" as "password1"
    And I enter "zaq1@WSX" as "confirmPassword"
    And I click "Zarejestruj" button
    Then I see alert with "Zarejestrowałeś/aś się" title
    And I click alert button