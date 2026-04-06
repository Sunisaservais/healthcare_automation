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
    Then the error message "Invalid username/password. Please try again." should be displayed
   # Then the required field message "Please fill out this field" should be displayed

    Examples:
      | username | password |
      |          | validPwd |
      | validUsr |          |

  Scenario: Password is masked by default
    When the user enters password "test123"
    Then the password should be masked by default

  Scenario: Verify "Can't login?" link is visible
    Then the "Can't log in?" link should be visible
    And the "Can't log in?" link should be clickable

  Scenario: Verify pop-up appears on clicking "Can't login?"
    When the user clicks on the "Can't log in?" link
    Then a confirmation pop-up should be displayed
    And the pop-up message should be "Please contact your System Administrator."


  Scenario: Verify pop-up contains "Okay" button
    When the user clicks on the "Can't log in?" link
    Then the confirmation pop-up should contain an "Okay" button
    And the "Okay" button should be enabled

  Scenario: Verify pop-up closes on clicking "Okay"
    When the user clicks on the "Can't log in?" link
    And the user clicks on the "Okay" button
    Then the confirmation pop-up should be closed
    And the user should remain on the login page

  Scenario: Verify pop-up does not persist after dismissal
    When the user clicks on the "Can't log in?" link
    And the user clicks on the "Okay" button
    Then the confirmation pop-up should not be visible
    And no overlay should remain on the screen

  Scenario: Verify pop-up does not persist after dismissal
    When the user clicks on the "Can't log in?" link
    And the user clicks on the "Okay" button
    Then the confirmation pop-up should not be visible
    And no overlay should remain on the screen

  Scenario: Verify pop-up appears again on repeated interaction
    When the user clicks on the "Can't log in?" link
    And the user clicks on the "Okay" button
    And the user clicks on the "Can't log in?" link again
    Then a confirmation pop-up should be displayed
    And the pop-up message should be "Please contact your System Administrator."

  Scenario: Verify keyboard interaction with pop-up
    When the user clicks on the "Can't log in?" link
    And the user presses the "Enter" key
    Then the confirmation pop-up should be closed

  Scenario: Verify escape key closes pop-up
    When the user clicks on the "Can't log in?" link
    And the user presses the "Escape" key
    Then the confirmation pop-up should be closed

  Scenario: Verify multiple clicks do not create duplicate pop-ups
    When the user rapidly clicks on the "Can't log in?" link multiple times
    Then only one confirmation pop-up should be displayed


  Scenario: User sees the eye icon in the password field when password is hidden
    Given the password is masked
    When the user clicks  on the eye icon
    Then the password  should be displayed

  Scenario: User sees the crossed eye icon in the password field when password is visible
    Given the password is visible
    When the user clicks on  the crossed eye icon
    Then the password is hidden in black bullet dots