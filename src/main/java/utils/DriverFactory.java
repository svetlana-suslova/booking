package utils;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by bonialvirto on 9/14/18.
 */
@Log4j
public class DriverFactory {

    public static WebDriver initDriver(String browser){
        WebDriver driver = null;
        if (browser.equals("chrome")) {
            ChromeDriverManager.getInstance().setup();
            log.info("Webdriver path: " + System.getProperty("webdriver.chrome.driver"));
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }

        if (browser.equals("browser")) {
            //code for another browser set up goes here
        }

        WebDriverRunner.setWebDriver(driver);
        return driver;
    }
}
