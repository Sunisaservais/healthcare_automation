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
                "html:target/cucumber-report/cucumber.html",
                "rerun:target/rerun.txt"
        },
        monochrome = true,
        dryRun = true,
        tags = "@ORION-415",
        publish = true
)
public class CukesRunnerTest {
}