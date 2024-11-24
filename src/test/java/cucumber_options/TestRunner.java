package cucumber_options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        dryRun = false,
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/report/cucumber_report.html",
                "junit:target/report/cucumber_report.xml",
                "json:target/report/cucumber_report.json"
        }
)
public class TestRunner {
}
