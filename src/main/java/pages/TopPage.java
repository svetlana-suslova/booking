package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Getter
public class TopPage {
    private SelenideElement logo = $(By.xpath("//img[@id='logo_no_globe_new_logo']"));
    private SelenideElement currencySelector = $(By.xpath("//div[@id='user_form']/ul/li[1]"));
    private SelenideElement currencySign = $(By.xpath("//div[@id='user_form']/ul/li[1]//input"));
    private SelenideElement euro = $(By.xpath("//div[@id='currency_dropdown_top']/ul[1]/li[2]"));
    private SelenideElement languageSelector = $(By.xpath("//div[@id='user_form']/ul/li[2]"));
    private SelenideElement languageFlag = $(By.xpath("//div[@id='user_form']/ul/li[2]//img"));
    private SelenideElement americanEnlish = $(By.xpath("//div[@id='current_language_foldout']/div[1]//ul[2]/li[2]"));
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
    public void languageFlagShouldBe(Condition condition){
        languageFlag.shouldBe(condition);
    }

    @Step
    public void currencySelectorClick(){
        currencySelector.click();
    }

    @Step
    public void languageSelectorClick(){
        languageSelector.click();
    }

    @Step
    public void euroSelect(){
        euro.click();
    }

    @Step
    public void americanEnlishSelect(){
        americanEnlish.click();
    }

    @Step
    public void closeAlert(){
        closeFlyAlert.click();
    }

}
