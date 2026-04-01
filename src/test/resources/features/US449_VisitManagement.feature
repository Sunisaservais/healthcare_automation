@ORION-449
Feature: Visit Management
  As a user : I should be able to start and end visits so that I can manage visits successfully

  Background:
    Given user is successfully logged in
    And the user navigates to the visit page

  @AC1 @smoke
  Scenario: Verify that the user is able to start a visit
    When the user clicks on the patient name
    When the user clicks on the Start Visit button
    And the user clicks on the Save button
    Then the visit should be started successfully
    And the user should see a success message or active visit record

  @AC2 @regression
  Scenario: Verify that there are 7 options under the Actions dropdown on the Visits page
    When the user clicks on the patient name
    And the user clics on the recent visits
    When the user clicks on the Actions dropdown
    Then the user should see the Actions dropdown displayed
    And the user should see exactly 7 options under the Actions dropdown

  @AC3 @regression
  Scenario: Verify that the user is able to end an active visit
    When the user clicks on the patient name
    And the user clicks on the End Visit button
    And the user confirms the end visit action
    Then the visit should be ended successfully
    And the visit status should be updated to no active visit


  Scenario: Verify user can start a visit
  Scenario: Verify Actions dropdown displays 7 options
  Scenario: Verify user can end a visit
