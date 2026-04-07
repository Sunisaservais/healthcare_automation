package com.healthcare.step_definitions;

import com.healthcare.pages.DashboardPage;
import com.healthcare.pages.LoginPage;
import com.healthcare.utilities.BrowserUtils;
import com.healthcare.utilities.ConfigurationReader;
import com.healthcare.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v140.audits.model.SRIMessageSignatureError;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class US409_LogoutFunctionality {

    WebDriverWait wait;
    DashboardPage dashboardPage;
    String newLanguage;
    String username = ConfigurationReader.getProperty("username");
    String originalPassword = ConfigurationReader.getProperty("password");
    String newPasswordAdmin1234 = originalPassword + "4";
    boolean passwordChanged = false;
    boolean loggedInWithNewPassword = false;
    String currentLanguage;
    String selectedLanguage;
    List<WebElement> optionElements;

    @When("the user hovers over the profile menu icon")
    public void the_user_hovers_over_the_profile_menu_icon() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        dashboardPage = new DashboardPage();
        By profileBtn = By.xpath("//li[@class='nav-item identifier']");
        WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(profileBtn));
        new Actions(Driver.getDriver())
                .moveToElement(profile)
                .pause(Duration.ofMillis(300))
                .perform();
    }

    @Then("the user should see {string} message")
    public void the_user_should_see_message(String message) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        By myAccountText = By.xpath("//ul[@id='user-account-menu']//a[contains(normalize-space(),'" + message + "')]");
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountText));
        Assert.assertEquals("Text mismatch", message, text.getText().trim());
    }

    @When("the user clicks on My Account menu icon")
    public void the_user_clicks_on_My_Account_menu_icon() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        By profileBtn = By.cssSelector("li.nav-item.identifier");
        By myAccountLocator = By.xpath("//ul[@id='user-account-menu']//a[normalize-space()='My Account']");

        WebElement profileMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(profileBtn)
        );
        try {
            wait.until(ExpectedConditions.elementToBeClickable(profileBtn)).click();
        } catch (Exception e) {
            new Actions(Driver.getDriver())
                    .moveToElement(profileMenu)
                    .pause(Duration.ofMillis(300))
                    .click()
                    .perform();
        }
        WebElement myAccount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(myAccountLocator)
        );
        try {
            myAccount.click();
        } catch (Exception e) {
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", myAccount);
        }
    }

    @Then("the user should see the following options in My Account menu")
    public void the_user_should_see_the_following_options_in_my_account_menu(List<String> expectedOptions) {
        dashboardPage = new DashboardPage();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfAllElements(dashboardPage.myAccountOptions));

        List<String> actualOptions = dashboardPage.myAccountOptions.stream()
                .map(e -> e.getText().replace("\n", " ").trim())
                .filter(t -> !t.isEmpty())
                .collect(Collectors.toList());

        for (String expected : expectedOptions) {
            Assert.assertTrue(actualOptions.stream().anyMatch(t -> t.equalsIgnoreCase(expected.trim())));
        }
    }

    @When("the user clicks on Logout")
    public void the_user_clicks_on_logout() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Logout']")
        )).click();
    }

    @When("the user click on My Languages")
    public void the_user_click_on_my_languages() {
        dashboardPage = new DashboardPage();
        dashboardPage.myLanguages.click();
    }

    @Then("the user should see the following languages")
    public void the_user_should_see_the_following_languages(List<String> expectedLanguages) {
        dashboardPage = new DashboardPage();

        String allText = dashboardPage.languagesContainer.getText().trim();

        List<String> actualLanguages = Arrays.stream(allText.split("\\r?\\n"))
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .toList();

        System.out.println("Actual: " + actualLanguages);
        System.out.println("Expected: " + expectedLanguages);

        Assert.assertTrue(actualLanguages.containsAll(expectedLanguages));
    }

    @When("the user clicks on Change language")
    public void the_user_clicks_on_change_language() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-extension-id='change-language']//button[normalize-space()='Change']")
        )).click();
    }

    @Then("the language list should be displayed")
    public void the_language_list_should_be_displayed() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[role='dialog']")
        ));
        Assert.assertTrue(dialog.isDisplayed());
    }

    @When("the user clicks on save button")
    public void the_user_clicks_on_save_button() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        // Get all language checkboxes
        List<WebElement> languages = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//input[@type='checkbox']")
        ));

        // Filter only displayed & enabled ones
        List<WebElement> validLanguages = languages.stream()
                .filter(WebElement::isDisplayed)
                .filter(WebElement::isEnabled)
                .collect(Collectors.toList());

        // Remove English if you don’t want default
        validLanguages = validLanguages.stream()
                .filter(e -> {
                    String label = e.findElement(By.xpath("./following-sibling::*")).getText();
                    return !label.equalsIgnoreCase("English");
                })
                .collect(Collectors.toList());

        if (validLanguages.isEmpty()) {
            throw new AssertionError("No valid languages available to select");
        }

        // Pick random
        int randomIndex = new Random().nextInt(validLanguages.size());
        WebElement randomLanguage = validLanguages.get(randomIndex);

        // Click if not already selected
        if (!randomLanguage.isSelected()) {
            randomLanguage.click();
        }

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@type='submit' and @value='Save']")
        ));
        saveButton.click();
    }

    @Then("the selected language should be updated")
    public void the_selected_language_should_be_updated() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        List<WebElement> error = Driver.getDriver().findElements(
                By.xpath("//div[@id='error-message']//p")
        );

        if (!error.isEmpty()) {
            String errorText = error.get(0).getText().trim();

            Assert.assertEquals("User defaults could not be updated.", errorText);
            Assert.fail("BUG: Language update failed - " + errorText);
        }

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[data-extension-id='user-menu-button'] button")
        )).click();

        String currentLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@aria-label='Change language']//p")
        )).getText().trim();

        System.out.println("newLanguage = " + newLanguage);
        System.out.println("currentLanguage = " + currentLanguage);

        Assert.assertEquals(newLanguage, currentLanguage);

    }

    @When("the user clicks on Change password")
    public void the_user_clicks_on_change_password() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        WebElement changePassword = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.//div[contains(@class,'task') and contains(normalize-space(.),'Change Password')]]")
        ));

        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", changePassword);
    }

    @When("the user enters old password")
    public void the_user_enters_old_password() {
        dashboardPage = new DashboardPage();
        dashboardPage.oldPassword.sendKeys(originalPassword);
    }

    @When("the user enters new password")
    public void the_user_enters_new_password() {
        dashboardPage = new DashboardPage();
        dashboardPage.newPassword.sendKeys(newPasswordAdmin1234);
    }

    @When("the user confirms the new password")
    public void the_user_confirms_the_new_password() {
        dashboardPage = new DashboardPage();
        dashboardPage.passwordConfirmation.sendKeys(newPasswordAdmin1234);
    }

    @When("the user clicks on Save new password button")
    public void the_user_clicks_on_save_new_password_button() {
        dashboardPage = new DashboardPage();
        dashboardPage.saveNewPasswordButton.click();
    }

    @When("the user logs out")
    public void the_user_logs_out() {
        dashboardPage = new DashboardPage();
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        dashboardPage = new DashboardPage();
        WebElement profileButton = wait.until(
                ExpectedConditions.elementToBeClickable(dashboardPage.profileIcon)
        );
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", profileButton);
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[normalize-space()='Logout']")
        ));
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", logoutButton);
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @When("the user logs in with the new password")
    public void the_user_logs_in_with_the_new_password() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@type='submit' and normalize-space()='Continue']")
        ));
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", continueBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(newPasswordAdmin1234);
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@type='submit' and normalize-space()='Log in']")
        ));
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", loginBtn);
        loggedInWithNewPassword = true;
    }

    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Home"));
        dashboardPage = new DashboardPage();
        dashboardPage.resetPasswordBackToOriginal(passwordChanged, loggedInWithNewPassword, originalPassword, newPasswordAdmin1234);
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("login"));
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(
                "User should be redirected to Login page after logout. Actual URL: " + currentUrl,
                currentUrl.contains("login"));
        System.out.println(currentUrl);
    }

    @When("the user clicks the browser back button")
    public void the_user_clicks_the_browser_back_button() {
        Driver.getDriver().navigate().back();
        BrowserUtils.waitFor(2);
    }

    @Then("the user should not be able to access the main page")
    public void the_user_should_not_be_able_to_access_the_main_page() {
        String currentUrl = Driver.getDriver().getCurrentUrl();
        System.out.println("Current URL after back: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("/login"));
    }

    @When("the user does not hover over the profile menu icon")
    public void the_user_does_not_hover_over_the_profile_menu_icon() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class,'identifier')]")
        ));
        List<WebElement> myAccountTooltips = Driver.getDriver().findElements(
                By.xpath("//span[@role='tooltip' and contains(normalize-space(.),'My Account')]")
        );
        if (myAccountTooltips.isEmpty()) {
            Assert.assertTrue(true);
        } else {
            WebElement tooltip = myAccountTooltips.get(0);
            String ariaHidden = tooltip.getAttribute("aria-hidden"); // "true" = hidden
            boolean hiddenByAria = "true".equalsIgnoreCase(ariaHidden);
            Assert.assertTrue(hiddenByAria || !tooltip.isDisplayed());
        }
    }

    @Then("the {string} message should not be visible")
    public void the_message_should_not_be_visible(String expectedMessage) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        By messageLocator = By.xpath("//a[normalize-space()='" + expectedMessage + "']");

        wait.until(driver -> {
            List<WebElement> elements = driver.findElements(messageLocator);

            if (elements.isEmpty()) {
                return true;
            }

            try {
                return !elements.get(0).isDisplayed();
            } catch (Exception e) {
                return true;
            }
        });

        List<WebElement> elementsAfter = Driver.getDriver().findElements(messageLocator);

        if (!elementsAfter.isEmpty()) {
            Assert.assertFalse(
                    "Message should not be visible: " + expectedMessage,
                    elementsAfter.get(0).isDisplayed()
            );
        }
    }

    @Then("the {string} message should be visible")
    public void the_message_should_be_visible(String expectedMessage) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        By profileBtn = By.xpath("//li[contains(@class,'identifier')]");
        By messageLocator = By.xpath("//a[normalize-space()='" + expectedMessage + "']");

        WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(profileBtn));

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(profileIcon)
                .pause(Duration.ofMillis(300))
                .perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        String actualMessage = message.getText().trim();

        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @When("the user moves the mouse away from the profile icon")
    public void the_user_moves_the_mouse_away_from_the_profile_icon() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));

        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        new Actions(Driver.getDriver())
                .moveToElement(body, 0, 0)
                .pause(Duration.ofMillis(300))
                .perform();
    }

    @Then("the My Account menu should be visible")
    public void the_my_account_menu_should_be_visible() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class,'cds--switcher__item-link')]")
        ));
    }

    @When("the user clicks outside the menu")
    public void the_user_clicks_outside_the_menu() {
        WebElement body = Driver.getDriver().findElement(By.tagName("body"));
        body.click();
    }

    @Then("the My Account menu should not be visible")
    public void the_my_account_menu_should_not_be_visible() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//a[contains(@class,'cds--switcher__item-link')]")
        ));
        List<WebElement> menuOptionsAfterClickOutside = Driver.getDriver().findElements(
                By.xpath("//a[contains(@class,'cds--switcher__item-link')]")
        );
        boolean menuStillVisible = !menuOptionsAfterClickOutside.isEmpty() &&
                menuOptionsAfterClickOutside.get(0).isDisplayed();
        Assert.assertFalse(menuStillVisible);
    }

    @When("the user logs in again with valid credentials")
    public void the_user_logs_in_again_with_valid_credentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }

    @When("the user opens the My Account menu and stores current language")
    public void the_user_opens_the_my_account_menu_and_stores_current_language() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[data-extension-id='user-menu-button'] button")
        )).click();
        currentLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-extension-id='change-language']//p")
        )).getText().trim();
    }

    @When("the user clicks Change language")
    public void the_user_clicks_change_language() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-extension-id='change-language']//button[normalize-space()='Change']")
        )).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[role='dialog']")
        ));
        optionElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//span[@class='cds--radio-button__label-text']")
                )
        );
    }

    @When("the user selects a different language")
    public void the_user_selects_a_different_language() {
        WebElement differentLanguage = optionElements.stream()
                .filter(e -> {
                    String t = e.getText().trim();
                    return !t.isEmpty()
                            && !t.equalsIgnoreCase("Change")
                            && !t.equalsIgnoreCase("Cancel")
                            && !t.equalsIgnoreCase(currentLanguage);
                })
                .findFirst()
                .orElseThrow(() -> new AssertionError("No different language found to select"));
        selectedLanguage = differentLanguage.getText().trim();
        differentLanguage.click();
    }

    @When("the user cancels the language change")
    public void the_user_cancels_the_language_change() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Cancel']")
        )).click();
    }

    @When("the user reopens the My Account menu")
    public void the_user_reopens_the_my_account_menu() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[data-extension-id='user-menu-button'] button")
        )).click();
    }

    @Then("the language should remain the same")
    public void the_language_should_remain_the_same() {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(20));
        String languageAfterCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-extension-id='change-language']//p")
        )).getText().trim();
        Assert.assertEquals(currentLanguage, languageAfterCancel,
                "Language should NOT update when user cancels the change. Selected (not saved): " + selectedLanguage);
    }
}
