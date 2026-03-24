package com.healthcare.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt",
        glue = "com.healthcare.step_definitions",
        plugin = {
                "pretty",
                "html:target/failed-cucumber-report.html"
        }
)
public class FailedTestRunner {
}
