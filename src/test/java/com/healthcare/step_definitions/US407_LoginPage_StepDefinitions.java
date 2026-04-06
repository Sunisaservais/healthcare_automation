package com.healthcare.step_definitions;

import com.healthcare.pages.DashboardPage;
import com.healthcare.pages.LoginPage;
import com.healthcare.utilities.BrowserUtils;
import com.healthcare.utilities.ConfigurationReader;
import com.healthcare.utilities.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class US407_LoginPage_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @Given("user navigates to the OpenMRS login page")
    public void user_navigates_to_the_open_mrs_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("base.url"));
    }

    @Then("the login page should be displayed")
    public void the_login_page_should_be_displayed() {
        Assert.assertTrue("Username field is not displayed", loginPage.usernameField.isDisplayed());
        Assert.assertTrue("Password field is not displayed", loginPage.passwordField.isDisplayed());
        Assert.assertTrue("Login button is not displayed", loginPage.loginButton.isDisplayed());
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        Assert.assertEquals(expectedTitle, Driver.getDriver().getTitle().trim());
    }

    @Then("the username field should be displayed")
    public void the_username_field_should_be_displayed() {
        Assert.assertTrue("Username field is not displayed", loginPage.usernameField.isDisplayed());
    }

    @Then("the password field should be displayed")
    public void the_password_field_should_be_displayed() {
        Assert.assertTrue("Password field is not displayed", loginPage.passwordField.isDisplayed());
    }

    @Then("the following session locations should be displayed:")
    public void the_following_session_locations_should_be_displayed(DataTable dataTable) {
        List<String> expectedLocations = dataTable.asList(String.class);

        for (String eachLocation : expectedLocations) {
            boolean found = false;

            for (WebElement eachElement : loginPage.locationOptions) {
                if (eachElement.getText().trim().equalsIgnoreCase(eachLocation)) {
                    found = true;
                    break;
                }
            }

            Assert.assertTrue("Location not found: " + eachLocation, found);
        }
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        loginPage.usernameField.clear();
        loginPage.usernameField.sendKeys(username);
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        loginPage.passwordField.clear();
        loginPage.passwordField.sendKeys(password);
    }

    @When("user selects {string} location")
    public void user_selects_location(String expectedLocation) {
        boolean clicked = false;

        for (WebElement eachLocation : loginPage.locationOptions) {
            if (eachLocation.getText().trim().equalsIgnoreCase(expectedLocation)) {
                eachLocation.click();
                clicked = true;
                break;
            }
        }

        Assert.assertTrue("Could not select location: " + expectedLocation, clicked);
    }

    @When("user clicks the login button")
    public void user_clicks_the_login_button() {
        loginPage.loginButton.click();
    }

    @Then("the location warning message should be {string}")
    public void the_location_warning_message_should_be(String expectedMessage) {
        Assert.assertTrue("Location warning message is not displayed", loginPage.sessionLocationError.isDisplayed());
        Assert.assertEquals(expectedMessage, loginPage.sessionLocationError.getText().trim());
    }

    @Then("the {string} link should be displayed")
    public void the_link_should_be_displayed(String expectedLinkText) {
        Assert.assertTrue("Can't log in link is not displayed", loginPage.cantLoginLink.isDisplayed());
        Assert.assertEquals(expectedLinkText, loginPage.cantLoginLink.getText().trim());
    }

    @When("user clicks the {string} link")
    public void user_clicks_the_link(String expectedLinkText) {
        Assert.assertEquals(expectedLinkText, loginPage.cantLoginLink.getText().trim());
        loginPage.cantLoginLink.click();
    }

    @Then("the help text should be {string}")
    public void the_help_text_should_be(String expectedText) {

          String actualText = loginPage.helpText.getText().trim();

        Assert.assertTrue(
                "Help text does not match",
                actualText.contains(expectedText)
        );

    }

    @Then("the {string} button should be displayed")
    public void the_button_should_be_displayed(String expectedButtonText) {
        Assert.assertTrue("Button is not displayed", loginPage.okButton.isDisplayed());
        Assert.assertEquals(expectedButtonText, loginPage.okButton.getText().trim());
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        BrowserUtils.waitFor(2);
        Assert.assertTrue("User was not logged in successfully", dashboardPage.profileIcon.isDisplayed());
    }

    @When("user clicks the password visibility toggle")
    public void user_clicks_the_password_visibility_toggle() {
        loginPage.passwordVisibilityToggle.click();
    }

    @When("user clicks the password visibility toggle again")
    public void user_clicks_the_password_visibility_toggle_again() {
        loginPage.passwordVisibilityToggle.click();
    }

    @Then("the password should be visible")
    public void the_password_should_be_visible() {
        Assert.assertEquals("text", loginPage.passwordField.getAttribute("type"));
    }

    @Then("the password should be hidden")
    public void the_password_should_be_hidden() {
        Assert.assertEquals("password", loginPage.passwordField.getAttribute("type"));
    }

    @When("user hovers over the password visibility toggle")
    public void user_hovers_over_the_password_visibility_toggle() {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(loginPage.passwordVisibilityToggle).perform();
    }

    @Then("the password visibility tooltip should be displayed")
    public void the_password_visibility_tooltip_should_be_displayed() {
        Assert.assertTrue(loginPage.passwordVisibilityToggle.isDisplayed());

    }

    @Then("the password visibility toggle should be displayed")
    public void thePasswordVisibilityToggleShouldBeDisplayed() {
        Assert.assertTrue(loginPage.passwordVisibilityToggle.isDisplayed());

    }
}