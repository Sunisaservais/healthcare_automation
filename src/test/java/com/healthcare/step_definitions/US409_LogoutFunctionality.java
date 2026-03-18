package com.healthcare.step_definitions;

import com.healthcare.pages.DashboardPage;
import com.healthcare.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class US409_LogoutFunctionality {

    WebDriverWait wait;
    DashboardPage dashboardPage;

    @When("the user hovers over the profile menu icon")
    public void the_user_hovers_over_the_profile_menu_icon() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        //Locate the profile menu/icon (top right).
        By profileBtn = By.cssSelector("div[data-extension-id='user-menu-button'] button");
        WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(profileBtn));

        //Hover the mouse over the profile menu/icon.
        new Actions(Driver.getDriver())
                .moveToElement(profile)
                .pause(Duration.ofMillis(300))
                .perform();
    }

    @Then("the user should see {string} message")
    public void the_user_should_see_message(String message) {
        By tooltip = By.xpath("//span[@role='tooltip' and contains(.,'My Account') and @aria-hidden='false']");
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(tooltip));

        //Verify that the “My Account” message appears
        Assert.assertEquals("Tooltip should be visible after hover", message, text.getText());
    }

    @When("the user clicks on the profile menu icon")
    public void the_user_clicks_on_the_profile_menu_icon() {
        dashboardPage = new DashboardPage();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        //Click the profile menu/icon to open My Account menu.
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.profileIcon)).click();

    }

    @Then("the user should see the following options in My Account menu")
    public void the_user_should_see_the_following_options_in_my_account_menu(List<String> expectedOptions) {
        dashboardPage = new DashboardPage();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfAllElements(dashboardPage.myAccountOptions));

        List<String> actualOptions = dashboardPage.myAccountOptions.stream()
                .map(e -> e.getText().replace("\n", " ").trim())
                .filter(t -> !t.isEmpty())
                .map(t -> t.replace("Change", "").trim())
                .collect(Collectors.toList());

        for (String expected : expectedOptions) {
            Assert.assertTrue(actualOptions.stream().anyMatch(t->t.equalsIgnoreCase(expected.trim())));
        }
    }

    @When("the user clicks on Logout")
    public void the_user_clicks_on_logout() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the default language should be {string}")
    public void the_default_language_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user clicks on Change language")
    public void the_user_clicks_on_change_language() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the language list should be displayed")
    public void the_language_list_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user selects a different language")
    public void the_user_selects_a_different_language() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user clicks on Change button")
    public void the_user_clicks_on_change_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the selected language should be updated")
    public void the_selected_language_should_be_updated() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user clicks on Change password")
    public void the_user_clicks_on_change_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user enters old password")
    public void the_user_enters_old_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user enters new password")
    public void the_user_enters_new_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user confirms the new password")
    public void the_user_confirms_the_new_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user clicks on Save button")
    public void the_user_clicks_on_save_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user logs out")
    public void the_user_logs_out() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user logs in with the new password")
    public void the_user_logs_in_with_the_new_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user clicks the browser back button")
    public void the_user_clicks_the_browser_back_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should not be able to access the main page")
    public void the_user_should_not_be_able_to_access_the_main_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user does not hover over the profile menu icon")
    public void the_user_does_not_hover_over_the_profile_menu_icon() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the {string} message should not be visible")
    public void the_message_should_not_be_visible(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the {string} message should be visible")
    public void the_message_should_be_visible(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user moves the mouse away from the profile icon")
    public void the_user_moves_the_mouse_away_from_the_profile_icon() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the My Account menu should be visible")
    public void the_my_account_menu_should_be_visible() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user clicks outside the menu")
    public void the_user_clicks_outside_the_menu() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the My Account menu should not be visible")
    public void the_my_account_menu_should_not_be_visible() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user logs in again with valid credentials")
    public void the_user_logs_in_again_with_valid_credentials() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user opens the My Account menu and stores current language")
    public void the_user_opens_the_my_account_menu_and_stores_current_language() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user clicks Change language")
    public void the_user_clicks_change_language() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user cancels the language change")
    public void the_user_cancels_the_language_change() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user reopens the My Account menu")
    public void the_user_reopens_the_my_account_menu() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the language should remain the same")
    public void the_language_should_remain_the_same() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
