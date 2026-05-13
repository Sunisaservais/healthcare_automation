package com.healthcare.step_definitions;

import com.healthcare.pages.DashboardPage;
import com.healthcare.pages.FindPatientRecord;
import com.healthcare.pages.RegisterAPatient;
import com.healthcare.utilities.BrowserUtils;
import com.healthcare.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class US449_VisitManagement {

    WebDriverWait wait;
    DashboardPage dashboardPage = new DashboardPage();
    RegisterAPatient registerAPatient = new RegisterAPatient();
    FindPatientRecord findPatientRecord = new FindPatientRecord();

    @Given("user is register a patient")
    public void user_is_register_a_patient() {
        registerAPatient.createUnidentifiedPatient("F");
    }

    @Given("the user navigates to the visits page")
    public void the_user_navigates_to_the_visits_page() {
        registerAPatient.homeButton.click();
        dashboardPage.findPatientRecordModule.click();
    }

    @When("the user clicks on the patient name")
    public void the_user_clicks_on_the_patient_name() {
        findPatientRecord.recentPatient.click();
    }

    @When("the user clicks on the start Visit button")
    public void the_user_clicks_on_the_start_visit_button() {
        findPatientRecord.startVisitButton.click();
    }

    @When("the user clicks on the confirm button")
    public void the_user_clicks_on_the_confirm_button() {
        findPatientRecord.startVisitConfirmButton.click();
    }

    @Then("the visit should be started successfully")
    public void the_visit_should_be_started_successfully() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'toast-item')]//p")
                )
        );
        wait.until(ExpectedConditions.textToBePresentInElement(toast, "started a visit"));
        Assert.assertTrue(toast.getText().contains("started a visit"));
    }

    @When("the user clicks on the recent visit")
    public void the_user_clicks_on_the_recent_visit() {
        findPatientRecord.recentVisits.click();
    }

    @When("the user clicks on the actions dropdown")
    public void the_user_clicks_on_the_actions_dropdown() {
        findPatientRecord.actionButton.click();
    }

    @Then("the user should see exactly {int} options under the actions dropdown")
    public void the_user_should_see_exactly_options_under_the_actions_dropdown(int expectedActionOptions) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(findPatientRecord.actionOptions));
        Assert.assertEquals(7, findPatientRecord.actionOptions.size());
    }

    @When("the user clicks on the end visit button")
    public void the_user_clicks_on_the_end_visit_button() {
        findPatientRecord.endVisit.click();
    }

    @When("the user confirms the end visit action")
    public void the_user_confirms_the_end_visit_action() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        By yesButton = By.xpath("//div[@id='end-visit-dialog']//button[contains(@class,'confirm') and normalize-space()='Yes']");
        WebElement yesBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

    @Then("the visit should be ended successfully")
    public void the_visit_should_be_ended_successfully() {
        Assert.assertTrue(findPatientRecord.noActiveVisit.isDisplayed());
    }

    @When("the user clicks on their profile name")
    public void the_user_clicks_on_their_profile_name() {
        findPatientRecord.patientNameProfile.click();
    }

    @When("the user deletes the patient profile")
    public void the_user_deletes_the_patient_profile() {
        findPatientRecord.deletePatient.click();
    }

    @When("the user provides a reason and confirms the deletion")
    public void the_user_provides_a_reason_and_confirms_the_deletion() {
        findPatientRecord.deleteReason.sendKeys("Test Reason");
        BrowserUtils.waitFor(1);
        findPatientRecord.reasonConfirmButton.click();
    }

    @Then("the patient should be deleted successfully")
    public void the_patient_should_be_deleted_successfully() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement toast = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'toast-item')]//p")
                )
        );
        Assert.assertTrue(toast.getText().contains("Patient has been deleted successfully"));
    }
}
