import com.codeborne.selenide.WebDriverRunner;
import utils.logging.SelenideAllureListener;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.BasicConfiguration;
import utils.DriverFactory;
import utils.PropertiesController;

import java.util.Properties;

@Log4j
@Listeners(value = SelenideAllureListener.class)
public class BaseTest {

    public static WebDriver driver;
   // public static BasicConfiguration configuration = new BasicConfiguration();
    Properties properties;

    @BeforeSuite
    public void beforeSuite() {
       // configuration = buildBasicConfiguration();
        properties = PropertiesController.getDefaultStartUpParams();
        driver = DriverFactory.initDriver(properties.getProperty("browser"));
    }

    protected void openWebSite(){
        String url = properties.getProperty("url");
        driver.get(url);
        log.info("WebSite opened: " + url);
    }

//    private BasicConfiguration buildBasicConfiguration() {
//
//       // properties.putAll(System.getProperties());
//
//        BasicConfiguration configs = new BasicConfiguration();
//        configs.setBrowser(properties.getProperty("browser"));
//        configs.setUrl(properties.getProperty("url"));
//        return configs;
//    }

    @AfterSuite
    public void afterSuite() {
        WebDriverRunner.getWebDriver().quit();
    }

}
