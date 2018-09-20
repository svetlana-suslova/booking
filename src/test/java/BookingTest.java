import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SearchBoxPage;
import pages.TopPage;

import static org.testng.Assert.assertTrue;

@Log4j
public class BookingTest extends BaseTest{
    private TopPage topPage = new TopPage();
    private SearchBoxPage searchBoxPage = new SearchBoxPage();

    @BeforeClass
    public void prepareForeTest(){
        openWebSite();
      //  topPage.closeAlert();
    }

    @Test
    public void testCurrencySelector(){
        // topPage.languageSelectorShouldBe(Condition.visible);
        topPage.currencySelectorClick();
        topPage.euroSelect();
        // topPage.languageFlagShouldBe(Condition.visible);
        currencyShouldBe("EUR");

    }

    @Test
    public void testLanguageSelector(){
       // topPage.languageSelectorShouldBe(Condition.visible);
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
       // topPage.languageFlagShouldBe(Condition.visible);
        flagShouldBe("us");

    }

    @Test
    public void testDestinationSelector(){
        topPage.currencySelectorClick();
        topPage.euroSelect();
        currencyShouldBe("EUR");
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
        flagShouldBe("us");
        searchBoxPage.typeDestination("Kiev");
        searchBoxPage.selectDestination(0);
        searchBoxPage.smallSearchFormShouldBe(Condition.hidden);
        searchBoxPage.selectLastDateOfCurrentMonth();
        searchBoxPage.bookingDetailsBlockOpen();
        searchBoxPage.selectAdults();
        searchBoxPage.selectChildren();
        searchBoxPage.selectChildAge();
        searchBoxPage.selectRoom();
      //  searchBoxPage.searchButtonClick();
        searchBoxPage.enableBusinessPurpose();
        searchBoxPage.searchButtonClick();
       // searchBoxPage.submitSearch();
    }

    @Test
    public void testDestinationSelectorByENTER(){
        topPage.currencySelectorClick();
        topPage.euroSelect();
        currencyShouldBe("EUR");
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
        flagShouldBe("us");
        searchBoxPage.getDestinationInput().clear();
        searchBoxPage.typeDestination("Kiev");
        Selenide.getFocusedElement().sendKeys(Keys.ENTER);
        searchBoxPage.smallSearchFormShouldBe(Condition.visible);
        searchBoxPage.selectLastDateOfCurrentMonth();
        searchBoxPage.selectAdults();
        searchBoxPage.selectChildren();
        searchBoxPage.selectChildAge();
        searchBoxPage.selectRoom();
        searchBoxPage.enableBusinessPurpose();
        searchBoxPage.submitSearch();
        searchBoxPage.checkInFieldShouldBe(Condition.visible);
       // checkInDateShouldbeApplied();

    }


    @Step
    public void flagShouldBe(String flag){
        assertTrue(topPage.getLanguageFlag().getAttribute("src").contains(flag));
    }

    @Step
    public void currencyShouldBe(String currency){
        assertTrue(topPage.getCurrencySign().getAttribute("value").equals(currency));
    }

    @Step
    public void checkInDateShouldbeApplied(){
        String expectedCheckInDate = "30 September 2018";
        assertTrue(searchBoxPage.getCheckInField().getText().contains(expectedCheckInDate));
    }

}
