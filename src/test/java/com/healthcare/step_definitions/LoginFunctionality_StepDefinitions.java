package com.healthcare.step_definitions;

import com.healthcare.pages.LoginPage;
import com.healthcare.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class LoginFunctionality_StepDefinitions {

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
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("home.page"));
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

    @Then("the Remember Me option should be visible")
    public void the_remember_me_option_should_be_visible() {

    }

    @Then("the Remember Me option should be clickable")
    public void the_remember_me_option_should_be_clickable() {
    }

    @Then("the password should be masked by default")
    public void the_password_should_be_masked_by_default() {
        Assert.assertEquals("password",loginPage.passwordField.getAttribute("type"));
    }

    @Then("the Forgot Password link should be visible")
    public void the_forgot_password_link_should_be_visible() {
    }

    @Then("the Forgot Password link should be clickable")
    public void the_forgot_password_link_should_be_clickable() {
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
        Assert.assertTrue(loginPage.eyeButton.isDisplayed());
    }

    @Given("the password is visible")
    public void the_password_is_visible() {
        loginPage.eyeButton.click();
        String actualType=loginPage.passwordField.getAttribute("type");
        Assert.assertEquals("text",actualType);
    }
}
