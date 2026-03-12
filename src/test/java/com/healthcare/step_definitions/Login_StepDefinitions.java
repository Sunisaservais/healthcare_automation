package com.healthcare.step_definitions;

import com.healthcare.pages.LoginPage;
import com.healthcare.utilities.BrowserUtils;
import com.healthcare.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Login_StepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        loginPage.login();
    }

    @Then("user should see the dashboard")
    public void user_should_see_the_dashboard() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("home"));
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("home"));
    }
}
