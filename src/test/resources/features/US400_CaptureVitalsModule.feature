@ORION-400 @smock
Feature: ORION-400 US07: Capture Vitals Module

  user story: as a user I should be able to add patient data using "Capture Vitals" module.

  Background:
    Given user is successfully logged in

  @smoke @ac-01 @ORION-400
  Scenario: Verify user can navigate Capture Vitals module
    When user clicks on "Capture Vitals" module
    Then user should be navigated to Capture Vitals page
    And user should see patient search field

  @smoke @ac-02 @ORION-400
  Scenario: Verify Capture Vital page is correct
    When user clicks on "Capture Vitals" module
    Then the page title should be "OpenMRS Electronic Medical Record"


