package com.healthcare.pages;

import com.healthcare.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class FindPatientRecord extends BasePage {

    public FindPatientRecord() {
        super(Driver.getDriver());
    }

    @FindBy(xpath = "//i[@class='ng-scope'][1]")
    public WebElement recentVisits;

    @FindBy(xpath = "//div[contains(@class,'actions dropdown actioncog')]//ul//li")
    public List<WebElement> actionOptions;

}
