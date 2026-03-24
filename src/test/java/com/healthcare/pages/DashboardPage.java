package com.healthcare.pages;

import com.healthcare.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage extends BasePage {

    public DashboardPage() {
        super(Driver.getDriver());
       //PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//li[@class='nav-item identifier']")
    public WebElement myAccount;

    @FindBy(xpath = "//li[contains(@class,'identifier')]")
    public WebElement profileIcon;

    @FindBy(xpath = "//div[@class='task']")
    public List<WebElement> myAccountOptions;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement changeButton;

    @FindBy(id = "oldPassword")
    public WebElement oldPassword;

    @FindBy(id = "newPassword")
    public WebElement newPassword;

    @FindBy(id = "passwordConfirmation")
    public WebElement passwordConfirmation;

    @FindBy(xpath = "//i[@class='icon-cog']")
    public WebElement myLanguages;

    @FindBy(xpath = "//input[@type='checkbox']")
    public List<WebElement> languagesOptions;

    @FindBy(xpath = "//div[contains(@class,'adminui-section-padded-top')]")
    public WebElement languagesContainer;

    WebDriverWait wait;



    public void resetPasswordBackToOriginal(boolean passwordChanged, boolean loggedInWithNewPassword, String originalPassword, String newPasswordAdmin1234) {
        if (!passwordChanged || !loggedInWithNewPassword) {
            return;
        }
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        try {
            // open profile menu
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[data-extension-id='user-menu-button'] button")
            )).click();

            // click second Change button (Password)
            List<WebElement> changeButtons = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//button[normalize-space()='Change']")
                    )
            );

            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", changeButtons.get(1));

            // old password
            WebElement oldPwd = wait.until(ExpectedConditions.elementToBeClickable(By.id("oldPassword")));
            oldPwd.click();
            oldPwd.clear();
            oldPwd.sendKeys(newPasswordAdmin1234);

            // new password
            WebElement newPwd = wait.until(ExpectedConditions.elementToBeClickable(By.id("newPassword")));
            newPwd.click();
            newPwd.clear();
            newPwd.sendKeys(originalPassword);

            // confirm password
            WebElement confirmPwd = wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordConfirmation")));
            confirmPwd.click();
            confirmPwd.clear();
            confirmPwd.sendKeys(originalPassword);

            // click Change
            WebElement confirmChangeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit' and .//span[normalize-space()='Change']]")
            ));
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", confirmChangeBtn);

            // logout
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[data-extension-id='user-menu-button'] button")
            )).click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Logout']")
            )).click();

        } catch (Exception e) {
            System.out.println("Could not reset password back to original: " + e.getMessage());
        }
    }
}
