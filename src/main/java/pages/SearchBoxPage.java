package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class SearchBoxPage {
    private SelenideElement destinationInput = $(By.xpath("//input[@id='ss']"));
    private ElementsCollection searchDestinationResult = $$(By.xpath("//form[@id='frm']//ul[1]/li/b"));
    private SelenideElement smallSearchForm = $(By.xpath("//form[@id='frm'][contains(@class, '-small')]"));
    private ElementsCollection calendarRows = $$(By.xpath(""));
    private ElementsCollection calendarCells = $$(By.xpath(""));


    //*[@id="frm"]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[3]/div/div/div[1]/table/tbody/tr[5]/td[7]
    //*[@id="frm"]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[3]/div/div/div[2]/table/tbody/tr[5]/td[3]
    @Step
    public void typeDestination(String destination){
        destinationInput.sendKeys(destination);
    }

    @Step
    public void selectDestination(int index){
        searchDestinationResult.get(index).click();
    }

    @Step
    public void smalSearchFormShouldBe(Condition condition){
        smallSearchForm.shouldBe(condition);

    }
}
