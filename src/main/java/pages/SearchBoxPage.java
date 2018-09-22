package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class SearchBoxPage {
    private SelenideElement destinationInput = $(By.xpath("//input[@id='ss']"));
    private ElementsCollection searchDestinationResult = $$(By.xpath("//form[@id='frm']//ul[1]/li/b"));
    private SelenideElement smallSearchForm = $(By.xpath("//form[@id='frm'][contains(@class, '-small')]"));
    private ElementsCollection calendarRows = $$(By.xpath(""));
    private ElementsCollection calendarCells = $$(By.xpath(""));
    private SelenideElement lastDateOfCurrentMonth = $(By.xpath("//table//th[@class='c2-month-header-monthname'][contains(text(),'Sep')]/following::tr[6]/td[7]"));
    private SelenideElement firstDateOfNextMonth = $(By.xpath("//table//th[@class='c2-month-header-monthname'][contains(text(),'October')]/following::tr[2]/td[1]"));
    private SelenideElement checkInField = $(By.xpath("//form[@id='frm']//div[@data-placeholder='Check-in date']"));
    private SelenideElement checkOutField = $(By.xpath("//form[@id='frm']//div[@class='sb-date-field__display'][@data-placeholder='Check-out date']"));
    private SelenideElement adultsSelection = $(By.xpath("//select[@id='group_adults']"));
    private SelenideElement childrenSelection = $(By.xpath("//select[@id='group_children']"));
    private SelenideElement childAgeSelection = $(By.xpath("//select[@name='age']"));
    private SelenideElement roomSelection = $(By.xpath("//select[@id='no_rooms']"));
    private SelenideElement bookingDetails = $(By.xpath("//label[@id='xp__guests__toggle']"));
    private SelenideElement travelPurposeCheckbox = $(By.xpath("//input[@name='sb_travel_purpose']"));
    private SelenideElement travelPurposeCheckbox2 = $(By.xpath("//form[@id='frm']/div[2]/label"));
    private SelenideElement searchButton = $(By.xpath("//form[@id='frm']/div[5]/div[2]/button"));
    private SelenideElement bigSearchButton = $(By.xpath("//form[@id='frm']/div[1]/div[4]/div[2]/button"));

    @Step
    public void typeDestination(String destination){
        destinationInput.sendKeys(destination);
    }

    @Step
    public void selectDestination(int index){
        searchDestinationResult.get(index).click();
    }

    @Step
    public void selectLastDateOfCurrentMonth(String month){

        Selenide.$(By.xpath("//table//th[@class='c2-month-header-monthname'][contains(text(),'" + month + "')]/following::tr[6]/td[7]")).click();
        //lastDateOfCurrentMonth.click();
    }

    @Step
    public void selectFirstDateOfNextMonth(){
        firstDateOfNextMonth.click();
    }

    @Step
    public void smallSearchFormShouldBe(Condition condition){
        smallSearchForm.shouldBe(condition);
    }

    @Step
    public void checkInFieldShouldBe(Condition condition){
        checkInField.shouldBe(condition);
    }
    @Step
    public void selectAdults(String adult){
        adultsSelection.selectOptionByValue(adult);
    }
    @Step
    public void selectChildren(String child){
        childrenSelection.selectOptionByValue(child);
    }

    @Step
    public void selectChildAge(String age){
        childAgeSelection.selectOptionByValue(age);
    }

    @Step
    public void selectRoom(String room){
        roomSelection.selectOptionByValue(room);
    }

    @Step
    public void enableBusinessPurpose(){
            travelPurposeCheckbox2.click();
    }

    @Step
    public void submitSearch(){
        searchButton.click();
    }

    @Step
    public void bookingDetailsBlockOpen(){
        bookingDetails.click();
    }
    @Step
    public void searchButtonClick(){
        bigSearchButton.click();
    }


}
