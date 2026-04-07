package com.healthcare.pages;

import com.healthcare.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAPatient extends BasePage {

    public RegisterAPatient() {
        super(Driver.getDriver());
    }

    @FindBy (id = "checkbox-unknown-patient")
    public WebElement checkboxUnknownPatient;

    @FindBy (id = "next-button")
    public WebElement nextButton;

    @FindBy (id = "submit")
    public  WebElement confirmButton;

    @FindBy (xpath = "//a[@href='/openmrs/referenceapplication/home.page']")
    public WebElement homeButton;
}
