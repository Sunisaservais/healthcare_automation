@ui @smoke @regression
Feature: ORION-407 Login Page Validation


  Scenario: AC01 User can land on Login page using base URL
    Given user navigates to the base URL
    Then login page should be displayed

  Scenario: AC02 URL is automatically redirected to login path
      Given user navigates to the base URL
      Then user should be redirected to login page url

  Scenario: AC03 Username section elements are visible
      Given user is on the login page
      Then "Username" label should be visible
      And username input field should be visible
      And "Continue" button should be visible

  Scenario: AC04 Password section appears after entering username
    Given user is on the login page
    When user enters username "admin"
    And user clicks "Continue" button
    Then "Password" label should be visible
    And password input field should be visible
    And "Log in" button should be visible

 Scenario: AC05 Show password tooltip appears on hover
   Given user is on password step
   When user moves mouse over the eye icon
   Then tooltip "Show password" should be displayed

   Scenario: AC06 Password visibility toggle changes tooltip text
     Given user is on password step
     When user clicks the eye icon
     Then tooltip text should be "Hide password"
     When user clicks the eye icon again
     Then tooltip text should be "Show password"

     Scenario: AC07 Learn more link opens "openmrs.org" in new tab
       Given user is on the login page
       When user clicks Learn more link
       Then a new tab should open
       And new tab URL should be "https://openmrs.org/"