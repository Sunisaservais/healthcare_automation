package com.healthcare.step_definitions;

import com.healthcare.utilities.ConfigurationReader;
import com.healthcare.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/*
In this class we will be able to create "pre" and "post" condition
for ALL the SCENARIOS and even STEPS.
 */
public class Hooks {

    //import the @Before coming from io.cucumber.java
    @Before(order = 1)
    public void setupMethod() {
        Driver.getDriver().get(ConfigurationReader.getProperty("base.url"));

    }

    //@Before (value = "@login", order = 2 )
    public void login_scenario_before() {
        System.out.println("---> @Before: RUNNING BEFORE EACH SCENARIO");
    }

    /*
    @After will be executed automatically after EVERY scenario in the project.
     */
    @After
    public void teardownMethod(Scenario scenario) {

        System.out.println("Scenario failed: " + scenario.isFailed());
        System.out.println("Driver null: " + (Driver.getDriver() == null));

        if (scenario.isFailed() && Driver.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        Driver.closeDriver();
    }

    //@BeforeStep
    public void setupStep() {
        System.out.println("-----> @BeforeSTEP : Running before each step!");
    }

    //@AfterStep
    public void teardownStep() {
        System.out.println("-----> @AfterSTEP : Running after each step!");
    }
}