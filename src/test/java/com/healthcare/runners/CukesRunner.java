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
                "html:target/cucumber-report/index.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        },
        dryRun = false,
        tags = "@ui",
        publish = true
)
public class CukesRunner {
}