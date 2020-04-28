Feature: Soldier's mission page

  Scenario: Displaying mission's table
    Given I am logged in as "soldier"
    When I go to missions page
    Then I see following missions
      | name            | groups                             | startDate  | endDate    |
      | Burza           | [Gromowladni, Gniewni]             | 2013-07-22 | W trakcie  |
      | Komuna paryska  | [Przepotezni, Bezlitosni, Gniewni] | 2017-10-19 | W trakcie  |
      | Pustynna zadyma | [Traperzy, Gniewni]                | 2013-07-22 | 2015-02-19 |