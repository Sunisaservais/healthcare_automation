package com.healthcare.pages;

import com.healthcare.utilities.ConfigurationReader;
import com.healthcare.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage {

    public LoginPage() {
        super(Driver.getDriver());
    }


    @FindBy(id = "username")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "Inpatient Ward")
    public WebElement inpatientWard;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Continue']")
    public WebElement continueButton;

    @FindBy(id = "loginButton")
    public WebElement loginButton;

    //-----------------For US_407---------------------
    //-----------------locations WebElements----------

    @FindBy(css = "#sessionLocation li")
    public List<WebElement> locationOptions;

    @FindBy(xpath = "//div[@id='error-message']")
    public WebElement loginErrorMessage;

    @FindBy(id="togglePassword")
    public WebElement eyeButton;

//
//    @FindBy(id = "Inpatient Ward")
//    public WebElement inpatientWardButton;
//
//    @FindBy(id="Outpatient Clinic")
//        public WebElement outpatientClinicButton;
//
//    @FindBy(id="Isolation Ward")
//    public WebElement isolationWardButton;
//
//
//    @FindBy(id="Pharmacy")
//    public WebElement pharmacyButton;
//
//    @FindBy(id="Laboratory")
//    public WebElement laboratoryButton;
//
//
//    @FindBy(id="Registration Desk")
//    public WebElement registrationDeskButton;

    //----------------------------------------------

    @FindBy(id="cantLogin")
    public WebElement cantLoginLink;

    @FindBy(xpath = "//div[contains(@class,'dialog-content')]")
    public WebElement helpText;

    @FindBy(xpath = "//button[@class='confirm']")
    public WebElement okButton;

    @FindBy(id="sessionLocation")
    public WebElement sessionLocationSection;

    @FindBy(id="sessionLocationError")
    public WebElement sessionLocationError;

    @FindBy(xpath ="//div[.='Invalid username/password. Please try again.']")
    public WebElement invalidCredentialsErrorMessage;

    @FindBy(id = "togglePassword")
    public WebElement passwordVisibilityToggle;




    public void login() {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(usernameField))
                .sendKeys(ConfigurationReader.getProperty("username"));

        wait.until(ExpectedConditions.visibilityOf(passwordField))
                .sendKeys(ConfigurationReader.getProperty("password"));

        wait.until(ExpectedConditions.visibilityOf(inpatientWard)).click();

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", loginButton);

    }
}
