package com.healthcare.step_definitions;

import com.healthcare.pages.DashboardPage;
import com.healthcare.pages.LoginPage;
import com.healthcare.utilities.ConfigurationReader;
import com.healthcare.utilities.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();


    @Given("user navigates to the OpenMRS login page")
    public void user_navigates_to_the_open_mrs_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("base.url"));
    }


    @Then("the login page should be displayed")
    public void the_login_page_should_be_displayed() {
        Assert.assertTrue(loginPage.usernameField.isDisplayed());

    }


    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedTitle));

    }


    @Then("the username field should be displayed")
    public void the_username_field_should_be_displayed() {
        Assert.assertTrue(loginPage.usernameField.isDisplayed());
    }


    @Then("the password field should be displayed")
    public void the_password_field_should_be_displayed() {
        Assert.assertTrue(loginPage.passwordField.isDisplayed());

    }

    @Then("the following session locations should be displayed:")
    public void the_following_session_locations_should_be_displayed(DataTable dataTable) {
        List<String> expectedLocations = dataTable.asList(String.class);

        for (String eachLocation : expectedLocations) {
            boolean found = false;

            for (WebElement eachElement : loginPage.locationOptions) {
                if (eachElement.getText().equalsIgnoreCase(eachLocation)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue("Location not found: " + eachLocation, found);
        }
    }

        @Then("the location warning message should be {string}")
        public void the_location_warning_message_should_be (String expectedMessage){
        Assert.assertEquals("Location warning message does not match", expectedMessage, loginPage.sessionLocationError.getText());

        }


        @Then("the {string} link should be displayed")
        public void the_link_should_be_displayed (String cantLoginLinkText){
        //"Can't log in?"
            Assert.assertTrue(loginPage.cantLoginLink.getText().contains(cantLoginLinkText));

        }

        @Then("the help text should be {string}")
        public void the_help_text_should_be (String buttonText){




        }

        //---------------------------------------------------------

//        @Then("the {string} button should be displayed")
//        public void the_button_should_be_displayed (String string){
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//
//        @When("user enters username {string}")
//        public void user_enters_username (String string){
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//        @When("user enters password {string}")
//        public void user_enters_password (String string){
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//        @When("user selects {string} location")
//        public void user_selects_location (String string){
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//        @When("user clicks the login button")
//        public void user_clicks_the_login_button () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//        @Then("user should be logged in successfully")
//        public void user_should_be_logged_in_successfully () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//
//        @When("user clicks the password visibility toggle")
//        public void user_clicks_the_password_visibility_toggle () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//        @Then("the password should be visible")
//        public void the_password_should_be_visible () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//
//        @When("user clicks the password visibility toggle")
//        public void user_clicks_the_password_visibility_toggle () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//        @When("user clicks the password visibility toggle again")
//        public void user_clicks_the_password_visibility_toggle_again () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//        @Then("the password should be hidden")
//        public void the_password_should_be_hidden () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//
//        @When("user hovers over the password visibility toggle")
//        public void user_hovers_over_the_password_visibility_toggle () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }
//
//        @Then("the password visibility tooltip should be displayed")
//        public void the_password_visibility_tooltip_should_be_displayed () {
//            // Write code here that turns the phrase above into concrete actions
//            throw new io.cucumber.java.PendingException();
//        }


    }
