package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class TopPage {
    private SelenideElement currencySign = $(By.xpath("//div[@id='user_form']/ul/li[1]//input"));
    private SelenideElement languageFlag = $(By.xpath("//div[@id='user_form']/ul/li[2]//img"));

    @Step
    public void currencySelectorClick(){
        Selenide.$(By.xpath("//div[@id='user_form']/ul/li[1]")).click();
    }

    @Step
    public void languageSelectorClick(){
        Selenide.$(By.xpath("//div[@id='user_form']/ul/li[2]")).click();
    }

    @Step
    public void euroSelect(){
        Selenide.$(By.xpath("//div[@id='currency_dropdown_top']/ul[1]/li[2]")).click();
    }

    @Step
    public void americanEnlishSelect(){
        Selenide.$(By.xpath("//div[@id='current_language_foldout']/div[1]//ul[2]/li[2]")).click();
    }
}
