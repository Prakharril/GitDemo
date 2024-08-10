@tag
Feature: Standalone test is working or not

  Background:
    Given I land on page

  @tag2
  Scenario Outline: Test checkout process
    Given Login with <name> and <password>
    When I add <productname> to the cart
    And I checkout <productname> and submit the order
    Then I verify "THANK YOU FOR THE ORDER." is displayed on the screen

    Examples:
      | name                | password    | productname |
      | shetty@gmail.com    | Iamking@000 | ZARA COAT   |
