package com.healthcare.pages;

import com.healthcare.utilities.BrowserUtils;
import com.healthcare.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterAPatient extends BasePage {

    public RegisterAPatient() {
        super(Driver.getDriver());
    }

    @FindBy(id = "checkbox-unknown-patient")
    public WebElement checkboxUnknownPatient;

    @FindBy(id = "next-button")
    public WebElement nextButton;

    @FindBy(id = "submit")
    public WebElement confirmButton;

    @FindBy(xpath = "//a[@href='/openmrs/referenceapplication/home.page']")
    public WebElement homeButton;

    public void createUnidentifiedPatient(String gender) {
        DashboardPage dashboardPage = new DashboardPage();
        RegisterAPatient registerAPatient = new RegisterAPatient();
        dashboardPage.registerAPatientModule.click();
        registerAPatient.checkboxUnknownPatient.click();
        Select genderDropdown = new Select(
                Driver.getDriver().findElement(By.xpath("//select[@id='gender-field']"))
        );
        genderDropdown.selectByValue(gender);
        registerAPatient.nextButton.click();
        BrowserUtils.waitFor(1);
        registerAPatient.confirmButton.click();
        BrowserUtils.waitFor(1);
        registerAPatient.homeButton.click();
    }
}
