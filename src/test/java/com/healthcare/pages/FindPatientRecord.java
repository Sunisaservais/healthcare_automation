package com.healthcare.pages;

import com.healthcare.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class FindPatientRecord extends BasePage {

    public FindPatientRecord() {
        super(Driver.getDriver());
    }

    @FindBy (xpath = "//tbody[@role='alert']//tr//td[2]")
    public WebElement recentPatient;

    @FindBy (xpath = "//div[@class='col-11 col-lg-10'][1]")
    public WebElement startVisitButton;

    @FindBy (id = "start-visit-with-visittype-confirm")
    public WebElement startVisitConfirmButton;

    @FindBy(xpath = "//a[@class='ng-binding'][1]")
    public WebElement recentVisits;

    @FindBy (xpath = "//span[@class='d-none d-sm-none d-md-inline d-lg-inline']")
    public WebElement actionButton;

    @FindBy(xpath = "//div[contains(@class,'actions dropdown actioncog')]//ul//li")
    public List<WebElement> actionOptions;

    @FindBy (xpath = "//i[@class='icon-off']")
    public  WebElement endVisit;

    @FindBy (xpath = "//h4[text()='No active visit']")
    public WebElement noActiveVisit;

    @FindBy (xpath = "//a[contains(@href,'patient.page?patientId=')]")
    public WebElement patientNameProfile;

    @FindBy (id = "org.openmrs.module.coreapps.deletePatient")
    public WebElement deletePatient;

    @FindBy (id = "delete-reason")
    public WebElement deleteReason;

    @FindBy (xpath = "//div[contains(@class,'modal')]//button[normalize-space()='Confirm']")
    public WebElement reasonConfirmButton;

}
