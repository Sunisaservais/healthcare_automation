package com.healthcare.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.healthcare.step_definitions",
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumber.html",
                "json:target/cucumber-report/cucumber.json",
                "junit:target/cucumber-report/cucumber.xml"
        },
        dryRun = false,
        tags = "",
        publish = true
)
public class CukesRunner {
}