@ui @login_access @regression
Feature: Login Access by Session Location

  Background:
    Given user navigates to the OpenMRS login page
    And user enters username "admin"
    And user enters password "Admin123"

  @regression
  Scenario Outline: Verify user log in successfully from each available location
    When user selects "<location>" location
    And user clicks the login button
    Then user should be logged in successfully

    Examples:
      | location          |
      | Inpatient Ward    |
      | Isolation Ward    |
      | Laboratory        |
      | Outpatient Clinic |
      | Pharmacy          |
      | Physiotherapy     |
      | Registration Desk |