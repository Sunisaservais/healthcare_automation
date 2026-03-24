package com.healthcare.step_definitions;

import com.healthcare.pages.LoginPage;
import com.healthcare.utilities.ConfigurationReader;
import com.healthcare.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class LoginPage_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    String mainWindowHandle;

    @Given("user navigates to the base URL")
    public void user_navigates_to_the_base_url() {
        Driver.getDriver().get(ConfigurationReader.getProperty("base.url"));
    }

    @Then("login page should be displayed")
    public void login_page_should_be_displayed() {
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));

        Assert.assertTrue(loginPage.usernameLabel.isDisplayed());
        Assert.assertTrue(loginPage.username.isDisplayed());
        Assert.assertTrue(loginPage.continueButton.isDisplayed());
        Assert.assertTrue(loginPage.learnMoreLink.isDisplayed());
    }

    @Then("user should be redirected to login page url")
    public void user_should_be_redirected_to_login_page_url() {
        String expectedUrl = ConfigurationReader.getProperty("base.url" + "openmrs/spa/login");

        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("base.url"));
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
    }

    @Then("{string} label should be visible")
    public void label_should_be_visible(String labelText) {
        if (labelText.equals("Username")) {
            wait.until(ExpectedConditions.visibilityOf(loginPage.usernameLabel));
            Assert.assertTrue(loginPage.usernameLabel.isDisplayed());
        } else if (labelText.equals("Password")) {
            wait.until(ExpectedConditions.visibilityOf(loginPage.passwordLabel));
            Assert.assertTrue(loginPage.passwordLabel.isDisplayed());
        } else {
            Assert.fail("Unsupported label: " + labelText);
        }
    }

    @And("username input field should be visible")
    public void username_input_field_should_be_visible() {
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
        Assert.assertTrue(loginPage.username.isDisplayed());
    }

    @And("{string} button should be visible")
    public void button_should_be_visible(String buttonText) {
        if (buttonText.equals("Continue")) {
            wait.until(ExpectedConditions.visibilityOf(loginPage.continueButton));
            Assert.assertTrue(loginPage.continueButton.isDisplayed());
        } else if (buttonText.equals("Log in")) {
            wait.until(ExpectedConditions.visibilityOf(loginPage.loginButton));
            Assert.assertTrue(loginPage.loginButton.isDisplayed());
        } else {
            Assert.fail("Unsupported button: " + buttonText);
        }
    }

    @When("user enters username {string}")
    public void user_enters_username(String usernameValue) {
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
        loginPage.username.clear();
        loginPage.username.sendKeys(usernameValue);
    }

    @And("user clicks {string} button")
    public void user_clicks_button(String buttonText) {
        if (buttonText.equals("Continue")) {
            wait.until(ExpectedConditions.elementToBeClickable(loginPage.continueButton)).click();
        } else if (buttonText.equals("Log in")) {
            wait.until(ExpectedConditions.elementToBeClickable(loginPage.loginButton)).click();
        } else {
            Assert.fail("Unsupported button: " + buttonText);
        }
    }

    @And("password input field should be visible")
    public void password_input_field_should_be_visible() {
        wait.until(ExpectedConditions.visibilityOf(loginPage.password));
        Assert.assertTrue(loginPage.password.isDisplayed());
    }

    @Given("user is on password step")
    public void user_is_on_password_step() {
        Driver.getDriver().get(ConfigurationReader.getProperty("base.url"));
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
        loginPage.username.sendKeys(ConfigurationReader.getProperty("username"));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.continueButton)).click();
        wait.until(ExpectedConditions.visibilityOf(loginPage.password));
    }

    @When("user moves mouse over the eye icon")
    public void user_moves_mouse_over_the_eye_icon() {
        wait.until(ExpectedConditions.visibilityOf(loginPage.eyeIcon));
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(loginPage.eyeIcon).perform();
    }

    @Then("tooltip {string} should be displayed")
    public void tooltip_should_be_displayed(String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(loginPage.tooltip));
        Assert.assertEquals(expectedText, loginPage.tooltip.getText().trim());
    }

    @When("user clicks the eye icon")
    public void user_clicks_the_eye_icon() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.eyeIcon)).click();
    }

    @Then("tooltip text should be {string}")
    public void tooltip_text_should_be(String expectedText) {
        // first trying tooltip
        try {
            wait.until(ExpectedConditions.visibilityOf(loginPage.tooltip));
            String actualText = loginPage.tooltip.getText().trim();
            Assert.assertEquals(expectedText, actualText);
            return;
        } catch (Exception ignored) {
        }

        // fallback: aria-label / title
        String ariaLabel = loginPage.eyeIcon.getAttribute("aria-label");
        String title = loginPage.eyeIcon.getAttribute("title");

        if (ariaLabel != null && !ariaLabel.isBlank()) {
            Assert.assertEquals(expectedText, ariaLabel.trim());
        } else if (title != null && !title.isBlank()) {
            Assert.assertEquals(expectedText, title.trim());
        } else {
            Assert.fail("Neither tooltip text nor aria-label/title was available.");
        }
    }

    @When("user clicks the eye icon again")
    public void user_clicks_the_eye_icon_again() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.eyeIcon)).click();
    }

    @When("user clicks Learn more link")
    public void user_clicks_learn_more_link() {
        mainWindowHandle = Driver.getDriver().getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.learnMoreLink)).click();
    }

    @Then("a new tab should open")
    public void a_new_tab_should_open() {
        wait.until(driver -> driver.getWindowHandles().size() == 2);
        Assert.assertEquals(2, Driver.getDriver().getWindowHandles().size());
    }

    @And("new tab URL should be {string}")
    public void new_tab_url_should_be(String expectedUrl) {
        Set<String> allWindows = Driver.getDriver().getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(mainWindowHandle)) {
                Driver.getDriver().switchTo().window(window);
                break;
            }
        }

        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}