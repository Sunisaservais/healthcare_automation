package com.healthcare.pages;

import com.healthcare.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CaptureVitalsPage extends BasePage {

    public CaptureVitalsPage() {
        super(Driver.getDriver());
    }


    @FindBy(xpath ="//a[@id = \"referenceapplication-vitals-referenceapplication-vitals-extension\"]")
    public WebElement captureVitalsModule;

    @FindBy (xpath = "//div[@class = 'logo']")
    public WebElement logo;

    @FindBy (xpath = "//i[contains(@class, 'icon-home')]")
    public WebElement homeButton;

    @FindBy (xpath = "//li[@class='nav-item identifier']")
    public WebElement userProfileDropdown;

    @FindBy (xpath = "//li[@class='change-location']")
    public WebElement locationDropdown;

    @FindBy (xpath ="//li[contains(@class, 'nav-item')]//a")
    public WebElement logoutButton;

    @FindBy (xpath = "//li[normalize-space(.)='Capture Vitals']")
    public WebElement captureVitalBreadcrumb;

    @FindBy (xpath = "//div[@id='content']//h2[contains(normalize-space(.),'Capture Vitals for Patient')]")
    public WebElement captureVitalHeader;

    @FindBy (xpath = "//input[@id='patient-search']")
    public WebElement patientSearchField;

    @FindBy (xpath = "//table[contains(@class,'dataTable')]")
    public WebElement patientTable;

    @FindBy (xpath = "//div[contains(text(), 'Showing')]")
    public WebElement patientSearchResultInfo;

}
