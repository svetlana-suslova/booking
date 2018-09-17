import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.BasicConfiguration;
import utils.DriverFactory;
import utils.PropertiesController;

import java.util.Properties;
@Log4j
public class BaseTest {

    public static WebDriver driver;
    public static BasicConfiguration configuration = new BasicConfiguration();

    @BeforeSuite
    public void beforeSuite() {
        configuration = buildBasicConfiguration();
        driver = DriverFactory.initDriver(configuration.getBrowser());
    }

    protected void openWebSite(){
        String url = configuration.getUrl();
        driver.get(url);
        log.info("WebSite opened: " + url);
    }

    private BasicConfiguration buildBasicConfiguration() {
        Properties properties = PropertiesController.getDefaultStartUpParams();
       // properties.putAll(System.getProperties());

        BasicConfiguration configs = new BasicConfiguration();
        configs.setBrowser(properties.getProperty("browser"));
        configs.setUrl(properties.getProperty("url"));
        return configs;
    }

    @AfterSuite
    public void afterSuite() {
        WebDriverRunner.getWebDriver().quit();
    }

}
