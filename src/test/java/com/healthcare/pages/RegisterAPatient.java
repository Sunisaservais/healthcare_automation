package com.healthcare.pages;

import com.healthcare.utilities.BrowserUtils;
import com.healthcare.model.Patient;
import com.healthcare.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    //-------------NAME--------------------
    @FindBy(xpath = "//a[contains(@id, 'referenceapplication-registrationapp-registerPatient-homepageLink')]")
    public WebElement registerPatientModule;

    @FindBy(xpath = "//li[@class='question-legend focused']")
    public WebElement nameLabel;

    @FindBy(xpath = "//input[@name='givenName']")
    public WebElement firstNameField;


    @FindBy(xpath = "//input[@name='middleName']")
    public WebElement middleNameField;


    @FindBy(xpath = "//input[@name='familyName']")
    public WebElement familyNameField;


    //-------------GENDER--------------------
    @FindBy(xpath = "//span[.='Gender']")
    public WebElement genderLabel;

    @FindBy(xpath = "//select[@id='gender-field']")
    public WebElement genderDropdown;

    @FindBy(xpath = "//select[@id='gender-field']/option[1] ")
    public WebElement maleOption;

    @FindBy(xpath = "//select[@id='gender-field']/option[2]")
    public WebElement femaleOption;


    //-------------BIRTHDAY--------------------

    @FindBy(xpath = "//span[@id='birthdateLabel'] ")
    public WebElement birthdayLabel;

    @FindBy(xpath = "//input[@id='birthdateDay-field']")
    public WebElement birthdateDayField;

    @FindBy(xpath = "//select[@id='birthdateMonth-field']")
    public WebElement birthdateMonthDropdown;

    //List<WebElement>
    @FindBy(xpath = "//select[@id='birthdateMonth-field']/option")
    public List<WebElement> monthOptions;

    @FindBy(xpath = "//select[@id='birthdateYear-field']")
    public WebElement birthdateYearField;

    //-------------ADDRESS--------------------

    @FindBy(xpath = "//li[@class='question-legend focused']")
    public WebElement addressLabel;

    @FindBy(xpath = "//input[@id='address1']")
    public WebElement address1Field;


    @FindBy(xpath = "//input[@id='cityVillage']")
    public WebElement cityVillageField;

    @FindBy(xpath = "//input[@id='stateProvince']")
    public WebElement stateProvinceField;

    @FindBy(xpath = "//input[@id='country']")
    public WebElement countryField;

    @FindBy(xpath = "//input[@id='postalCode']")
    public WebElement postalCodeField;

    //-------------PhoneNumber--------------------

    @FindBy(xpath = "//span[.='Phone Number']")
    public WebElement phoneNumberLabel;

    @FindBy(xpath = "//input[@id='fr4714-field']")
    public WebElement phoneNumberField;

    //-------------RELATIVES--------------------

    @FindBy(xpath = "//span[.='Relatives']")
    public WebElement relativesLabel;

    @FindBy(xpath = "//select[@id='relationship_type']")
    public WebElement relationshipDropdown;

    //List<WebElement>
    @FindBy(xpath = "//select[@id='relationship_type']/option")
    public List<WebElement> relationshipTypeOption;


    @FindBy(xpath = "//input[@placeholder='Person Name']")
    public WebElement personNameField;

//    @FindBy (xpath = " ")
//    public WebElement


    // ---------- ACTION METHODS ----------

    public void createPatient(Patient patient) {
        registerPatientModule.click();

        familyNameField.sendKeys(patient.getFamilyName());
        middleNameField.sendKeys(patient.getMiddleName());
        familyNameField.sendKeys(patient.getFamilyName());
        nextButton.click();

        Select genderSelect = new Select(genderDropdown);
        genderSelect.selectByVisibleText(patient.getGender());
        nextButton.click();

        birthdateDayField.sendKeys(patient.getBirthDay());

        Select selectMonth = new Select(birthdateMonthDropdown);
        selectMonth.selectByVisibleText(patient.getBirthMonth());
        nextButton.click();

        birthdateYearField.sendKeys(patient.getBirthYear());
        nextButton.click();

        address1Field.sendKeys(patient.getAddress());
        cityVillageField.sendKeys(patient.getCity());
        stateProvinceField.sendKeys(patient.getState());
        countryField.sendKeys(patient.getCountry());
        postalCodeField.sendKeys(patient.getCountry());
        nextButton.click();

        phoneNumberField.sendKeys(patient.getPhoneNumber());
        nextButton.click();

        Select relationshipSelect = new Select(relationshipDropdown);
        relationshipSelect.selectByVisibleText(patient.getRelationshipType());
        nextButton.click();

        personNameField.sendKeys(patient.getPhoneNumber());
        nextButton.click();

        confirmButton.click();

    }

}
