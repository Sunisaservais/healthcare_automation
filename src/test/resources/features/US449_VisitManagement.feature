@ORION-449
Feature: Visit Management
  As a user, I should be able to start and end visits
  so that I can manage visits successfully

  Background:
    Given user is successfully logged in

  @ORION-452
  Scenario: Verify that the user is able to start a visit
    And user is register a patient
    And the user navigates to the visits page
    When the user clicks on the patient name
    And the user clicks on the start Visit button
    And the user clicks on the confirm button
    Then the visit should be started successfully

  @ORION-453
  Scenario: Verify that there are 7 options under the Actions dropdown on the Visits page
    And the user navigates to the visits page
    When the user clicks on the patient name
    And the user clicks on the recent visit
    And the user clicks on the actions dropdown
    Then the user should see exactly 7 options under the actions dropdown

  @ORION-454
  Scenario: Verify that the user is able to end an active visit
    And the user navigates to the visits page
    When the user clicks on the patient name
    And the user clicks on the recent visit
    And the user clicks on the end visit button
    And the user confirms the end visit action
    Then the visit should be ended successfully
    When the user clicks on their profile name
    And the user deletes the patient profile
    And the user provides a reason and confirms the deletion
    Then the patient should be deleted successfully