
@tag
Feature: Error validation
  I want to use this template for my feature file

  @Error
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed 

    Examples: 
      | name  								 | password 	| 
      | ajithkumar63@gmail.com | Ajith@135  | 
