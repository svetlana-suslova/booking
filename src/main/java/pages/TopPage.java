package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TopPage {
    private SelenideElement currencySelector = $(By.xpath("//li[@class='user_center_option uc_currency js-uc-currency']"));
    private SelenideElement languageSelector = $(By.xpath("//div[@id='user_form']/ul/li[2]"));
    private SelenideElement logo = $(By.xpath("//img[@id='logo_no_globe_new_logo']"));
    private SelenideElement closeFlyAlert = $(By.xpath("//div[@class='bicon bicon-aclose header-signin-prompt__close']"));

    @Step
    public void currencySelectorBe(Condition condition){
        currencySelector.shouldBe(condition);
    }

    @Step
    public void languageSelectorShouldBe(Condition condition){
        languageSelector.shouldBe(condition);
    }
    @Step
    public void languageSelectorClick(){
        languageSelector.click();
    }

    @Step
    public void closeAlert(){
        closeFlyAlert.click();
    }


}
