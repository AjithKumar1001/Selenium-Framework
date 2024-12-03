
@tag
Feature: Purchase the Order From E-Commerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Purchasing the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name  								 | password 	 | productName     |
      | ajithkumar63@gmail.com | Ajith@1235  | ZARA COAT 3     |
      | mukeshk@gmail.com      | Mukesh@1235 | ADIDAS ORIGINAL |
