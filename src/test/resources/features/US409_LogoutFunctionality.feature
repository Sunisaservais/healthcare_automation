Feature: Log out Functionality

  Background:
    Given user is successfully logged in

  #AC1 - Verify that the user see "My Account" message when user hovers over profile menu.
  @smoke
  Scenario: Verify that user sees "My Account" message when hovering over profile menu

    When the user hovers over the profile menu icon
    Then the user should see "My Account" message

  #AC2 - Verify that the user can see the options below when user clicks My Account profile menu.
  @smoke
  Scenario: Verify My Account dropdown displays expected options
    When the user clicks on the profile menu icon
    Then the user should see the following options in My Account menu
      | Super User |
      | English    |
      | Password   |
      | Logout     |

  #AC3 - Verify that the user can log out and land on to "Log in" page by clicking "Logout" button in My Account menu.
  Scenario: Verify that user can successfully log out
    When the user clicks on the profile menu icon
    And the user clicks on Logout
    Then the user should be redirected to the login page

  #AC4 - Verify that English is the default "Language".
  Scenario: Verify that English is the default language
    When the user clicks on the profile menu icon
    Then the default language should be "English"

  #AC5 - Verify that user can change the "Language" by clicking "Change" link and select the language from the list of 13 languages.
  Scenario: Verify that user can change the language
    When the user clicks on the profile menu icon
    And the user clicks on Change language
    Then the language list should be displayed
    When the user selects a different language
    And the user clicks on Change button
    Then the selected language should be updated

  #AC6 - Verify user can change password and login with new password
  Scenario: Verify that user can change password and login with new password
    When the user clicks on the profile menu icon
    And the user clicks on Change password
    And the user enters old password
    And the user enters new password
    And the user confirms the new password
    And the user clicks on Save button
    And the user logs out
    And the user logs in with the new password
    Then the user should be logged in successfully

  #AC7 - Verify that user cannot return back to main page by clicking back button after logging out.
  Scenario: Verify user cannot return to main page after logout using browser back button
    When the user clicks on the profile menu icon
    And the user clicks on Logout
    Then the user should be redirected to the login page
    When the user clicks the browser back button
    Then the user should not be able to access the main page

  #NEG_AC1 - 01 Message should NOT appear before hover
  Scenario: Verify that "My Account" message does NOT appear before hover
    When the user does not hover over the profile menu icon
    Then the "My Account" message should not be visible

  #NEG_AC1 - 02 Message should disappear after mouse moves away
  Scenario: Verify tooltip disappears after mouse moves away
    When the user hovers over the profile menu icon
    Then the "My Account" message should be visible
    When the user moves the mouse away from the profile icon
    Then the "My Account" message should not be visible

  #NEG_AC2 - 01 Menu should close when clicking outside
  Scenario: Verify menu closes when clicking outside
    When the user clicks on the profile menu icon
    Then the My Account menu should be visible
    When the user clicks outside the menu
    Then the My Account menu should not be visible

  #NEG_AC4 - 01 Default language should not change after logout/login
  Scenario: Verify default language remains English after logout and login
    Then the default language should be "English"
    When the user logs out
    And the user logs in again with valid credentials
    Then the default language should be "English"

  #NEG_AC5 - 01 Cancel language change should NOT update language
  Scenario: Verify canceling language change does not update language
    When the user opens the My Account menu and stores current language
    And the user clicks Change language
    And the user selects a different language
    And the user cancels the language change
    And the user reopens the My Account menu
    Then the language should remain the same