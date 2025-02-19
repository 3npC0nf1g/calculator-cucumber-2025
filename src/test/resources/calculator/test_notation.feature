Feature: Integer Arithmetic Expressions
  This feature provides a range of scenarios corresponding to the
  intended external behaviour of arithmetic expressions on integers.

  # This is just a comment.
  # You can start with a Background: that will be run before executing each scenario.

  Background:
    Given I initialise a calculator

  # Each scenario can be seen as a test that can be executed with JUnit,
  # provided that each of the steps (Given, When, And and Then) are
  # implemented in a Java mapping file (CalculatorSteps.Java)



    Scenario: Printing the multiplication of two integer numbers
      Given the multiplication of two numbers 8 and 6
      Then its INFIX notation is ( 8 * 6 )
      And its PREFIX notation is * (8, 6)
      And its POSTFIX notation is (8, 6) *

    Scenario: Printing the division of two integer numbers
        Given the division of two numbers 8 and 6
        Then its INFIX notation is ( 8 / 6 )
        And its PREFIX notation is / (8, 6)
        And its POSTFIX notation is (8, 6) /

  Scenario Outline: Printing arithmetic operations with two integer parameters
    Given an integer operation <op>
    When I provide a first number <n1>
    And I provide a second number <n2>
    And I provide a notation string <n3>
    Then the operation prints to <result>

    Examples:
      | op  | n1 | n2 | n3        | result    |
      | "+" | 4  | 5  | "PREFIX"  | + (4, 5)  |
      | "-" | 8  | 5  | "POSTFIX" | (8, 5) -  |
      | "*" | 7  | 2  | "INFIX"   | ( 7 * 2 ) |
      | "/" | 6  | 2  | "PREFIX"  | / (6, 2)  |
