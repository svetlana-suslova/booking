package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
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
    private SelenideElement lastDateOfCurrentMonth = $(By.xpath("//table//th[@class='c2-month-header-monthname'][contains(text(),'September')]/following::tr[6]/td[7]"));
    private SelenideElement firstDateOfNextMonth = $(By.xpath("//table//th[@class='c2-month-header-monthname'][contains(text(),'October')]/following::tr[2]/td[1]"));
    private SelenideElement checkInField = $(By.xpath("//form[@id='frm']//div[@data-placeholder='Check-in date']"));
    private ElementsCollection bookingDates = $$(By.xpath("//div[@class='sb-date-field__display']"));
    private SelenideElement adults = $(By.xpath("//select[@id='group_adults']"));
    private SelenideElement adultsSelection = $(By.xpath("//select[@id='group_adults']/option[@value='1' and @selected='selected'] | //label[@id='xp__guests__toggle']/span/span[1]"));
    private SelenideElement children = $(By.xpath("//select[@id='group_children']"));
    private SelenideElement childrenSelection = $(By.xpath("//select[@id='group_children']/option[@value='1' and @selected='selected'] | //label[@id='xp__guests__toggle']//span[2]/span"));
    private SelenideElement childAge = $(By.xpath("//select[@name='age']"));
    private SelenideElement childAgeSelection = $(By.xpath("//select[@name='age']/option[@value='5' and @selected='selected']"));
    private SelenideElement rooms = $(By.xpath("//select[@id='no_rooms']"));
    private SelenideElement roomsSelection = $(By.xpath("//select[@id='no_rooms']/option[@value='1' and @selected='selected']"));
    private SelenideElement bookingDetails = $(By.xpath("//label[@id='xp__guests__toggle']"));

    private SelenideElement businessPurposeCheckbox = $(By.xpath("//form[@id='frm']/div[2]/label"));
   // private SelenideElement businessPurposeCheckboxChecked = $(By.xpath("//input[@name='sb_travel_purpose' and @checked='checked']"));

   // private SelenideElement searchButton = $(By.xpath("//form[@id='frm']/div[5]/div[2]/button"));
    private SelenideElement searchButton = $(By.xpath("//form[@id='frm']/div[1]/div[4]/div[2]/button"));
    private SelenideElement selectedDestination = $(By.xpath("//input[@id='ss' and @value='Kiev']"));

    private String checkInDate;
    private String checkOutDate;

    @Step
    public void typeInAndSelectDestination(String destination){
        destinationInput.sendKeys(destination);
        searchDestinationResult.get(0).click();
    }

    @Step
    public void selectDestination(int index){
        searchDestinationResult.get(index).click();
    }

    @Step
    public void selectLastDateOfCurrentMonth(String month){
        Selenide.$(By.xpath("//table//th[@class='c2-month-header-monthname'][contains(text(),'"
                + month + "')]/following::tr[6]/td[7]")).click();
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
        adults.selectOptionByValue(adult);
    }


    @Step
    public void selectChildren(String child){
        children.selectOptionByValue(child);
    }

    @Step
    public void selectChildAge(String age){
        childAge.selectOptionByValue(age);
    }

    @Step
    public void selectRooms(String room){
        rooms.selectOptionByValue(room);
    }

    @Step
    public void enableBusinessPurpose(){
            businessPurposeCheckbox.click();
    }

//    @Step
//    public void submitSearch(){
//        searchButton.click();
//    }

    @Step
    public void bookingDetailsBlockOpen(){
        bookingDetails.click();
    }
    @Step
    public void submitSearch(){
        searchButton.click();
    }

    public void whatIsTheCheckInDate(){
        checkInDate = bookingDates.get(0).getText();
    }

    public void whatIsTheCheckOutDate(){
        checkOutDate = bookingDates.get(1).getText();
    }

    @Step
    public void selectedDestinationShouldBe(String destination, Condition condition){
        Selenide.$(By.xpath("//input[@id='ss' and @value='" + destination + "']")).shouldBe(condition);
    }

    @Step
    public void selectedAdultsShouldBe(String adult, Condition condition){
        Selenide.$(By.xpath("//select[@id='group_adults']/option[@value='" + adult + "' and @selected='selected'] " +
                "| //label[@id='xp__guests__toggle']/span/span[1]")).shouldBe(condition);
    }

    @Step
    public void selectedChildrenShouldBe(String child, Condition condition) {
        Selenide.$(By.xpath("//select[@id='group_children']/option[@value='" + child + "' and @selected='selected'] " +
                "| //label[@id='xp__guests__toggle']//span[2]/span")).shouldBe(condition);
    }

    @Step
    public void selectedChildAgeShouldBe(String age, Condition condition){
        Selenide.$(By.xpath("//select[@name='age']/option[@value='" + age + "' and @selected='selected']"))
                .shouldBe(condition);
    }

    @Step
    public void selectedRoomsShouldBe(String room, Condition condition){
        Selenide.$(By.xpath("//select[@id='no_rooms']/option[@value='" + room + "' and @selected='selected']"))
                .shouldBe(condition);
    }

    @Step
    public void businessPurposeShouldBeEnabled(Condition condition){
        Selenide.$(By.xpath("//input[@name='sb_travel_purpose' and @checked='checked']")).shouldBe(condition);
    }
}
