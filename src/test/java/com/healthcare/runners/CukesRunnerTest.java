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
                "json:target/cucumber.json",
                "junit:target/cucumber.xml",
                "html:target/cucumber-report/cucumber.html"
        },
        monochrome = true,
        dryRun = false,
        tags = "@smoke",
        publish = true
)
public class CukesRunnerTest {
}