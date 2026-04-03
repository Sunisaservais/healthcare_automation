@ORION_407
Feature: ORION-407 Login Page Validation

  # User Story: As a user, I want to access and use the OpenMRS login page,
  # so that I can  verify page elements,
  # session location, page controls,
  # and basic login access.
  Background:
  Given user navigates to the OpenMRS login page

  @smoke @ac01 @ORION-432
  Scenario: Verify user lands on the Login page successfully
    Then the login page should be displayed

  @smoke @ac02 @ORION-433
  Scenario: Verify page title is correct
    Then the page title should be "Login"

  @smoke @ac03 @ORION-434
  Scenario: Verify username field is displayed
    Then the username field should be displayed

  @smoke @ac04 @ORION-437
  Scenario: Verify password field is displayed
    Then the password field should be displayed

  @regression @ac05 @ORION-438
  Scenario: Verify all available session locations are displayed
    Then the following session locations should be displayed:
      | Inpatient Ward    |
      | Isolation Ward    |
      | Laboratory        |
      | Outpatient Clinic |
      | Pharmacy          |
      | Registration Desk |

  @regression @ac06 @ORION-439
  Scenario: Verify location warning message is displayed
    When user enters username "admin"
    And user enters password "Admin123"
    And user clicks the login button
    Then the location warning message should be "You must choose a location!"

  @regression @ac07 @ORION-440
  Scenario: Verify help section content is displayed
    Then the "Can't log in?" link should be displayed
    When user clicks the "Can't log in?" link
    Then the help text should be "Please contact your System Administrator."
    And the "Okay" button should be displayed

  @smoke @ac08 @ORION-441
  Scenario: Verify user can log in successfully after selecting a session location
    When user enters username "admin"
    And user enters password "Admin123"
    And user selects "Inpatient Ward" location
    And user clicks the login button
    Then user should be logged in successfully

  @regression @ac09 @ORION-442
  Scenario: Verify password visibility toggle is displayed
    Then the password visibility toggle should be displayed

  @regression @ac09.1 @ORION-443
  Scenario: Verify password becomes visible after clicking visibility toggle
    When user enters password "Admin123"
    And user clicks the password visibility toggle
    Then the password should be visible

  @regression @ac09.2 @ORION-444
  Scenario: Verify password becomes hidden after clicking visibility toggle again
    When user enters password "Admin123"
    And user clicks the password visibility toggle
    And user clicks the password visibility toggle again
    Then the password should be hidden

  @regression @ac09.3 @ORION-445
  Scenario: Verify password visibility toggle tooltip is displayed on hover
    When user hovers over the password visibility toggle
    Then the password visibility tooltip should be displayed

