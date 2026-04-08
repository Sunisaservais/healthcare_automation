package com.healthcare.step_definitions;

import com.healthcare.pages.CaptureVitalsPage;
import com.healthcare.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class US400_CaptureVitals_StepDefinitions {


    LoginPage loginPage = new LoginPage();
    CaptureVitalsPage captureVitalsPage = new CaptureVitalsPage();


    @When("user clicks on {string} module")
    public void user_clicks_on_module(String moduleName) {

    captureVitalsPage.captureVitalsModule.click();

    }

    @Then("user should be navigated to Capture Vitals page")
    public void user_should_be_navigated_to_capture_vitals_page() {

        Assert.assertTrue(captureVitalsPage.patientSearchField.isDisplayed());

    }

    @Then("user should see patient search field")
    public void user_should_see_patient_search_field() {
        Assert.assertTrue(captureVitalsPage.patientSearchField.isDisplayed());

    }

}
