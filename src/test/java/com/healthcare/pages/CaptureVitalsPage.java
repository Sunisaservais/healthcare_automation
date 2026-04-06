package com.healthcare.pages;

import com.healthcare.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CaptureVitalsPage extends BasePage {

    public CaptureVitalsPage() {
        super(Driver.getDriver());
    }


    @FindBy(id = "referenceapplication-vitals-referenceapplication-vitals-extension")
    public WebElement captureVitalsModule;


    @FindBy (id="patient-search")
    public WebElement parientSearchField;


}
