package com.healthcare.step_definitions;

import com.healthcare.pages.LoginPage;
import com.healthcare.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginFunctionality_StepDefinitions {

    private static final Log log = LogFactory.getLog(LoginFunctionality_StepDefinitions.class);
    LoginPage loginPage = new LoginPage();

    @Given("the user is on the OpenMRS login page")
    public void the_user_is_on_the_open_mrs_login_page() {
        //No code needed Hooks already opens the URL
    }

    @When("the user enters a valid username")
    public void the_user_enters_a_valid_username() {
        loginPage.usernameField.sendKeys("admin");
    }

    @When("the user enters a valid password")
    public void the_user_enters_a_valid_password() {
        loginPage.passwordField.sendKeys("Admin123");
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.inpatientWard.click();
        loginPage.loginButton.click();
    }

    @Then("the user should be redirected to the OpenMRS main page")
    public void the_user_should_be_redirected_to_the_open_mrs_main_page() {
        System.out.println("Current URL"+ Driver.getDriver().getCurrentUrl());
        assertTrue(Driver.getDriver().getCurrentUrl().contains("home.page"));
    }

    @When("the user presses Enter")
    public void the_user_presses_enter() {
        loginPage.inpatientWard.click();
        loginPage.loginButton.sendKeys(Keys.ENTER);
    }

    @When("the user enters an invalid username")
    public void the_user_enters_an_invalid_username() {
        loginPage.usernameField.sendKeys("wrongUser");
    }

    @When("the user enters an invalid password")
    public void the_user_enters_an_invalid_password() {
        loginPage.passwordField.sendKeys("wrongPass");
    }

    @Then("the error message {string} should be displayed")
    public void the_error_message_should_be_displayed(String expectedMessage) {
        Assert.assertEquals(expectedMessage,loginPage.loginErrorMessage.getText());
    }

    @When("the user enters username {string}")
    public void the_user_enters_username(String username) {

        loginPage.usernameField.sendKeys(username);
    }

    @When("the user enters password {string}")
    public void the_user_enters_password(String password) {
        loginPage.passwordField.sendKeys(password);
    }

  /*  @Then("the required field message {string} should be displayed")
    public void the_required_field_message_should_be_displayed(String missingFieldMessage) {
        Assert.assertEquals(missingFieldMessage,loginPage.loginErrorMessage.getText());
    }

*/

    @Then("the {string} link should be visible")
    public void the_link_should_be_visible(String linkText) {
        assertTrue(loginPage.cantLogin.isDisplayed());
        Assert.assertEquals(linkText,loginPage.cantLogin.getText());
    }

    @Then("the {string} link should be clickable")
    public void the_link_should_be_clickable(String linkText) {
        assertTrue(loginPage.cantLogin.isEnabled());
        Assert.assertEquals(linkText,loginPage.cantLogin.getText());
    }

    @When("the user clicks on the {string} link")
    public void the_user_clicks_on_the_link(String linkText) {
        Assert.assertEquals(linkText, loginPage.cantLogin.getText());
        loginPage.cantLogin.click();
    }

    @Then("a confirmation pop-up should be displayed")
    public void a_confirmation_pop_up_should_be_displayed() {
        assertTrue(loginPage.cannotLoginPopup.isDisplayed());
    }

    @Then("the pop-up message should be {string}")
    public void the_pop_up_message_should_be(String expectedMessage) {
        assertTrue(loginPage.cannotLoginPopup.isDisplayed());
        Assert.assertEquals(expectedMessage, loginPage.popupMessage.getText());

    }

    @Then("the confirmation pop-up should contain an {string} button")
    public void the_confirmation_pop_up_should_contain_an_button(String buttonText) {
            assertTrue(loginPage.okButton.isDisplayed());
            Assert.assertEquals(buttonText, loginPage.okButton.getText());
    }


    @Then("the {string} button should be enabled")
    public void the_button_should_be_enabled(String button) {
        assertTrue(loginPage.okButton.isEnabled());
    }

    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String button) {
        Assert.assertEquals(button, loginPage.okButton.getText());
        assertTrue(loginPage.okButton.isDisplayed());
        assertTrue(loginPage.okButton.isEnabled());
        loginPage.okButton.click();

    }

    @Then("the confirmation pop-up should be closed")
    public void the_confirmation_pop_up_should_be_closed() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(loginPage.cannotLoginPopup)));
    }

    @Then("the user should remain on the login page")
    public void the_user_should_remain_on_the_login_page() {
        assertTrue(Driver.getDriver().getCurrentUrl().contains("login"));
        assertTrue(loginPage.cantLogin.isDisplayed());
    }


    @Then("the confirmation pop-up should not be visible")
    public void the_confirmation_pop_up_should_not_be_visible() {
        Assert.assertFalse(loginPage.cannotLoginPopup.isDisplayed());

    }

    @Then("no overlay should remain on the screen")
    public void no_overlay_should_remain_on_the_screen() {
        Assert.assertFalse(loginPage.cannotLoginPopup.isDisplayed());


    }

    @When("the user clicks on the {string} link again")
    public void the_user_clicks_on_the_link_again(String cantLogButt) {
        loginPage.cantLogin.click();

    }


    @When("the user presses the {string} key")
    public void the_user_presses_the_key(String key) {
        if (key.equalsIgnoreCase("Enter")) {
            loginPage.okButton.sendKeys(Keys.ENTER);
        } else if (key.equalsIgnoreCase("Escape")) {
            loginPage.okButton.sendKeys(Keys.ESCAPE);
        }
    }


    @When("the user rapidly clicks on the {string} link multiple times")
    public void the_user_rapidly_clicks_on_the_link_multiple_times(String linkText) {
        Assert.assertEquals(linkText, loginPage.cantLogin.getText());

        for (int i = 0; i < 5; i++) {
            try {
                if (loginPage.cantLogin.isDisplayed() && loginPage.cantLogin.isEnabled()) {
                    loginPage.cantLogin.click();
                }
            } catch (ElementClickInterceptedException e) {
                break;
            }
        }
    }

    @Then("only one confirmation pop-up should be displayed")
    public void only_one_confirmation_pop_up_should_be_displayed() {
        assertTrue(loginPage.cannotLoginPopup.isDisplayed());
    }

    @When("the user clicks  on the eye icon")
    public void the_user_clicks_on_the_eye_icon() {
        loginPage.eyeButton.click();
    }

    @Then("the password  should be displayed")
    public void the_password_should_be_displayed() {
        String type = loginPage.passwordField.getAttribute("type");
        Assert.assertEquals("text", type);
    }

    @When("the user clicks on  the crossed eye icon")
    public void the_user_clicks_on_the_crossed_eye_icon() {
        Assert.assertTrue(loginPage.eyeButton.isDisplayed());
        Assert.assertTrue(loginPage.eyeButton.isEnabled());
        loginPage.eyeButton.click();
    }

    @Then("the password is hidden in black bullet dots")
    public void the_password_is_hidden_in_black_bullet_dots() {
        String type = loginPage.passwordField.getAttribute("type");
        Assert.assertEquals("password", type);
    }

    @Then("the password should be masked by default")
    public void the_password_should_be_masked_by_default() {
        Assert.assertEquals("password",loginPage.passwordField.getAttribute("type"));
    }

    @Given("the password is masked")
    public void the_password_is_masked() {
        Assert.assertEquals("password",loginPage.passwordField.getAttribute("type"));
    }

    @When("the user hovers over the eye icon")
    public void the_user_hovers_over_the_eye_icon() {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(loginPage.eyeButton).perform();
    }

    @Then("the tooltip {string} should be displayed")
    public void the_tooltip_should_be_displayed(String string) {
        assertTrue(loginPage.eyeButton.isDisplayed());
    }

    @Given("the password is visible")
    public void the_password_is_visible() {
        loginPage.eyeButton.click();
        String actualType=loginPage.passwordField.getAttribute("type");
        Assert.assertEquals("text",actualType);
    }
}
