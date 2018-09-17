package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class SearchBoxPage {
    private SelenideElement logo = $(By.xpath(""));
    private SelenideElement aa = $(By.xpath(""));
    private ElementsCollection calendarRows = $$(By.xpath(""));
    private ElementsCollection calendarCells = $$(By.xpath(""));
    private SelenideElement cc = $(By.xpath(""));
    private SelenideElement bb = $(By.xpath(""));
    private SelenideElement dd = $(By.xpath(""));


    @Step
    public void currencySelectorClick(){
        aa.click();
    }

    @Step
    public void languageSelectorClick(){
        bb.click();
    }


    @Step
    public void americanEnlishSelect(){
        cc.click();
    }

    @Step
    public void closeAlert(){
        dd.click();
    }
}
