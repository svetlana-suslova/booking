import com.codeborne.selenide.WebDriverRunner;
import utils.logging.SelenideAllureListener;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.DriverFactory;
import utils.PropertiesController;

import java.util.Properties;

@Log4j
@Listeners(value = SelenideAllureListener.class)
public class BaseTest {

    public static WebDriver driver;
    Properties properties;

    @BeforeSuite
    public void beforeSuite() {
        properties = PropertiesController.getDefaultStartUpParams();
        driver = DriverFactory.initDriver(properties.getProperty("browser"));
    }

    protected void openWebSite(){
        String url = properties.getProperty("url");
        driver.get(url);
        log.info("Web page opening: " + url);
    }

    @AfterSuite
    public void afterSuite() {
        WebDriverRunner.getWebDriver().quit();
    }

}
