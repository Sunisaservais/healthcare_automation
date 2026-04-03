package com.healthcare.pages;

import com.healthcare.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage(WebDriver driver) {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
