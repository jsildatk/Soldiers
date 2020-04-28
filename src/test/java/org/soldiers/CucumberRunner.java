package org.soldiers;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "org.soldiers", plugin = { "pretty", "junit:target/cucumber/cucumber.xml",
        "json:target/cucumber/cucumber.json" }, features = "src/test/resources")
public class CucumberRunner {
}
