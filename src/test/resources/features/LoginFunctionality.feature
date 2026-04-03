@Orion-408
Feature: Login functionality

  Background:
    Given the user is on the OpenMRS login page

  Scenario: User logs in successfully using the Login button
    When the user enters a valid username
    And the user enters a valid password
    And the user clicks the login button
    Then the user should be redirected to the OpenMRS main page

  Scenario: User logs in successfully using the Enter key
    When the user enters a valid username
    And the user enters a valid password
    And the user presses Enter
    Then the user should be redirected to the OpenMRS main page

  Scenario: User sees an error message for invalid credentials
    When the user enters an invalid username
    And the user enters an invalid password
    And the user clicks the login button
    Then the error message "Invalid username/password. Please try again." should be displayed

  Scenario Outline: User sees required field validation when login fields are empty
    When the user enters username "<username>"
    And the user enters password "<password>"
    And the user clicks the login button
   # Then the required field message "Please fill out this field" should be displayed

    Examples:
      | username | password |
      |          | validPwd |
      | validUsr |          |

  Scenario: User sees the "Remember Me" option
    Then the Remember Me option should be visible
    And the Remember Me option should be clickable

  Scenario: Password is masked by default
    When the user enters password "test123"
    Then the password should be masked by default

  Scenario: User sees the Forgot Password link
    Then the Forgot Password link should be visible
    And the Forgot Password link should be clickable

  Scenario: User sees Show password tooltip when password is hidden
    Given the password is masked
    When the user hovers over the eye icon
    Then the tooltip "Show password" should be displayed

  Scenario: User sees Hide password tooltip when password is visible
    Given the password is visible
    When the user hovers over the eye icon
    Then the tooltip "Hide password" should be displayed