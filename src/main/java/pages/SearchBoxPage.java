package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class SearchBoxPage {
    private ElementsCollection bookingDates = $$(By.xpath("//div[@class='sb-date-field__display']"));
    private String checkInDate;
    private String checkOutDate;

    @Step
    public void typeInAndSelectDestination(String destination){
        Selenide.$(By.xpath("//input[@id='ss']")).sendKeys(destination);
        Selenide.$$(By.xpath("//form[@id='frm']//ul[1]/li/b")).get(0).click();
    }

    @Step
    public void selectLastDateOfCurrentMonth(String month){
        Selenide.$(By.xpath("//table//th[@class='c2-month-header-monthname'][contains(text(),'"
                + month + "')]/following::tr[6]/td[7]")).click();
    }

    @Step
    public void selectAdults(String adult){
        Selenide.$(By.xpath("//select[@id='group_adults']")).selectOptionByValue(adult);
    }

    @Step
    public void selectChildren(String child){
        Selenide.$(By.xpath("//select[@id='group_children']")).selectOptionByValue(child);
    }

    @Step
    public void selectChildAge(String age){
        Selenide.$(By.xpath("//select[@name='age']")).selectOptionByValue(age);
    }

    @Step
    public void selectRooms(String room){
        Selenide.$(By.xpath("//select[@id='no_rooms']")).selectOptionByValue(room);
    }

    @Step
    public void enableBusinessPurpose(){
        Selenide.$(By.xpath("//form[@id='frm']/div[2]/label")).click();
    }

    @Step
    public void bookingDetailsOpen(){
        Selenide.$(By.xpath("//label[@id='xp__guests__toggle']")).click();
    }
    @Step
    public void searchButtonClick(){
        Selenide.$(By.xpath("//form[@id='frm']/div[1]/div[4]/div[2]/button")).click();
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
