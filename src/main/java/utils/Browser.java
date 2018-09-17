package utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;


public class Browser {

    @Step
    public void open(String url) {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            DriverFactory.initDriver(System.getProperty("env.browser"));
        }
        Selenide.open(url);
    }

    @Step
    public void quite() {
        if (WebDriverRunner.hasWebDriverStarted()) Selenide.close();
    }

    @Step
    public void pressEnter() {
       Selenide.getFocusedElement().sendKeys(Keys.ENTER);
    }

}
