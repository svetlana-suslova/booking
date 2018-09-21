package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by bonialvirto on 9/20/18.
 */
public class FilterPage {
    private SelenideElement filterPriceOption1 = $(By.xpath("//div[@id='filter_price']/div[2]/a[1]"));
    private SelenideElement filterPriceOption2 = $(By.xpath("//div[@id='filter_price']/div[2]/a[2]"));
    private SelenideElement filterPriceOption3 = $(By.xpath("//div[@id='filter_price']/div[2]/a[3]"));
    private SelenideElement filterPriceOption4 = $(By.xpath("//div[@id='filter_price']/div[2]/a[4]"));
    private SelenideElement filterReveiwOption1 = $(By.xpath("//div[@id='filter_review']/div[2]/a[1]"));
    private SelenideElement filterReveiwOption2 = $(By.xpath("//div[@id='filter_review']/div[2]/a[2]"));


    @Step
    public void selectPriceOption1(){
        filterPriceOption1.click();
    }

    @Step
    public void selectPriceOption2(){
        filterPriceOption2.click();
    }

    @Step
    public void selectPriceOption3(){
        filterPriceOption3.click();
    }

    @Step
    public void selectPriceOption4(){
        filterPriceOption4.click();
    }

    @Step
    public void selectReveiwOption1(){
        filterReveiwOption1.click();
    }

    @Step
    public void selectReveiwOption2(){
        filterReveiwOption2.click();
    }
}
