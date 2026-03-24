package com.healthcare.pages;

import com.healthcare.utilities.ConfigurationReader;
import com.healthcare.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "Inpatient Ward")
    public WebElement inpatientWard;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Continue']")
    public WebElement continueButton;

    @FindBy(id = "loginButton")
    public WebElement loginButton;

    public void login() {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(username))
                .sendKeys(ConfigurationReader.getProperty("username"));

        wait.until(ExpectedConditions.visibilityOf(password))
                .sendKeys(ConfigurationReader.getProperty("password"));

        wait.until(ExpectedConditions.visibilityOf(inpatientWard)).click();

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", loginButton);

    }
}
