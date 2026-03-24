@ORION-409 @smoke
Feature: Log out Functionality

  Background:
    Given user is successfully logged in

  #AC1 - Verify that the user see "My Account" message when user hovers over profile menu.
  #Passed
  @ORION-410
  Scenario: Verify that user sees "My Account" message when hovering over profile menu
    When the user hovers over the profile menu icon
    Then the user should see "My Account" message

  #AC2 - Verify that the user can see the options below when user clicks My Account profile menu.
  #Passed
  @ORION-411
  Scenario: Verify My Account displays expected options
    When the user clicks on My Account menu icon
    Then the user should see the following options in My Account menu
      | Change Password |
      | My Languages    |

  @ORION-412
  #AC3 - Verify that the user can log out and land on to "Log in" page by clicking "Logout" button in My Account menu.
  #Passed
  Scenario: Verify that user can successfully log out
    When the user clicks on Logout
    Then the user should be redirected to the login page

  @ORION-413
  #AC4 - Verify that user can see all the languages
  #Passed
  Scenario: Verify that English is the default language
    When the user clicks on My Account menu icon
    And the user click on My Languages
    Then the user should see the following languages
      | English                  |
      | English (United Kingdom) |
      | Spanish                  |
      | French                   |
      | Italian                  |
      | Portuguese               |

  @ORION-414
  #AC5 - Verify that user can change the "Language" by clicking "Change" link and select the language from the list
  #this Scenario will not working in the new environment
#  Scenario: Verify that user can change the language
#    When the user clicks on My Account menu icon
#    And the user click on My Languages
#    Then the user should see the following languages
#      | English                  |
#      | English (United Kingdom) |
#      | Spanish                  |
#      | French                   |
#      | Italian                  |
#      | Portuguese               |
#    And the user clicks on Change button
#    Then the selected language should be updated

  @ORION-415
  #AC6 - Verify user can change password and login with new password
  #this Scenario I will hold on until we make sure the application is stable
#  Scenario: Verify that user can change password and login with new password
#    When the user clicks on My Account menu icon
#    And the user clicks on Change password
#    And the user enters old password
#    And the user enters new password
#    And the user confirms the new password
#    And the user clicks on Save button
#    And the user logs out
#    And the user logs in with the new password
#    Then the user should be logged in successfully

  @ORION-416
  #AC7 - Verify that user cannot return back to main page by clicking back button after logging out.
  #Passed
  Scenario: Verify user cannot return to main page after logout using browser back button
    And the user clicks on Logout
    Then the user should be redirected to the login page
    When the user clicks the browser back button
    Then the user should not be able to access the main page

  @ORION-418
  #NEG_AC1 - 01 Message should NOT appear before hover
  #Passed
  Scenario: Verify that "My Account" message does NOT appear before hover
    When the user does not hover over the profile menu icon
    Then the "My Account" message should not be visible

  @ORION-419
  #NEG_AC1 - 02 Message should disappear after mouse moves away
  #Passed
  Scenario: Verify tooltip disappears after mouse moves away
    When the user hovers over the profile menu icon
    Then the "My Account" message should be visible
    When the user moves the mouse away from the profile icon
    Then the "My Account" message should not be visible
