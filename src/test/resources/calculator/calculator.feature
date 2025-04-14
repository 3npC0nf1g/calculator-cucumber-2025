Feature: Integer Arithmetic Expressions
  This feature provides a range of scenarios corresponding to the
  intended external behaviour of arithmetic expressions on integers.

  Background:
    Given I initialise a calculator

  Scenario: Adding two integer numbers
    Given an integer operation '+'
    When I provide a first number 4
    And I provide a second number 5
    Then the operation evaluates to 9

  Scenario: Subtracting two integer numbers
    Given an integer operation '-'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 2

  Scenario: Multiplying two integer numbers
    Given an integer operation '*'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 35

  Scenario: Dividing two integer numbers
    Given an integer operation '/'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 1

  Scenario: Printing the sum of two integer numbers
    Given the sum of two numbers 8 and 6
    Then its INFIX notation is ( 8 + 6 )
    And its PREFIX notation is + (8, 6)
    And its POSTFIX notation is (8, 6) +

  Scenario: Evaluation arithmetic operations over a list of integer numbers
    Given the following list of integer numbers
      | 8 | 2 | 2 |
    Then the sum is 12
    And the product is 32
    And the difference is 4
    And the quotient is 2

  Scenario Outline: Adding two integer numbers
    Given an integer operation '+'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |4|5|9|
      |5|3|8|

  Scenario Outline: Dividing two integer numbers
    Given an integer operation '/'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |35|5|7|
      |7|5|1|
      |5|7|0|

  Scenario Outline: Evaluating arithmetic operations with two integer parameters
    Given an integer operation <op>
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      | op  |n1|n2|result|
      | "+" | 4| 5|     9|
      | "-" | 8| 5|     3|
      | "*" | 7| 2|    14|
      | "/" | 6| 2|     3|

  Scenario Outline: Displaying a composite expression with consistent notation
    Given a composite expression consisting of the addition of 3 and 4 and multiplication by 5
    When I set the notation to <notation>
    Then the expression displayed in <notation> notation is <expected>

    Examples:
      | notation   | expected             |
      | "PREFIX"   | "* (+ (3, 4), 5)"    |
      | "INFIX"    | "( ( 3 + 4 ) * 5 )"  |
      | "POSTFIX"  | "((3, 4) +, 5) *"    |

  Scenario: Adding two real numbers
    Given a real operation '+'
    When I provide a first real number 3.14
    And I provide a second real number 2.86
    Then the operation evaluates to 6.00

  Scenario: Dividing two real numbers
    Given a real operation '/'
    When I provide a first real number 5.0
    And I provide a second real number 2.0
    Then the operation evaluates to 2.5

  Scenario: Adding two complex numbers
    Given a complex operation '+'
    When I provide a first complex number "2.0+3.0i"
    And I provide a second complex number "1.0+2.0i"
    Then the operation evaluates to "3.0+5.0i"

  Scenario: Multiplying two complex numbers
    Given a complex operation '*'
    When I provide a first complex number "2.0+3.0i"
    And I provide a second complex number "4.0+-1.0i"
    Then the operation evaluates to "11.0+10.0i"

  Scenario: Dividing two complex numbers
    Given a complex operation '/'
    When I provide a first complex number "5.0+3.0i"
    And I provide a second complex number "2.0+1.0i"
    Then the operation evaluates to "2.6+0.2i"


  Scenario: Converting degrees to radians
    Given I have an angle of 180 degrees
    When I convert the angle to radians
    Then the result should be "3.141592653589793"

  Scenario Outline: Converting degrees to radians
    Given I have an angle of <degrees> degrees
    When I convert the angle to radians
    Then the result should be <radians>

    Examples:
      | degrees | radians                |
      | 0       | 0.0                    |
      | 90      | 1.5707963267948966     |
      | 180     | 3.141592653589793      |
      | 360     | 6.283185307179586      |