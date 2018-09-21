import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.WebElementsCollection;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FilterPage;
import pages.SearchBoxPage;
import pages.SearchResultsPage;
import pages.TopPage;

import java.util.*;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

@Log4j
public class BookingTest extends BaseTest{
    private TopPage topPage = new TopPage();
    private SearchBoxPage searchBoxPage = new SearchBoxPage();
    private FilterPage filterPage = new FilterPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();

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
        filterPage.selectPriceOption1();
        filterPage.selectPriceOption2();
        filterPage.selectPriceOption3();
        filterPage.selectPriceOption4();
        filterPage.selectReveiwOption1();
        filterPage.selectReveiwOption2();
        searchResultsPage.searchResultsTableShouldBe(Condition.visible);
        searchResultsPage.loaderShouldBe(Condition.hidden);
        collectFoundReviewScoreBages();
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
       // searchBoxPage.checkInFieldShouldBe(Condition.visible);
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

    @Step
    public void collectFoundReviewScoreBages(){
        int bageCount = searchResultsPage.getReviewScoreBage().size();
        float[] bageScores = new float[bageCount];
        for (int i=0; i<bageCount; i++){
            String bageScore = searchResultsPage.getReviewScoreBage().get(i).getText();
            log.info("Bage score = " + bageScore);
            bageScores[i] = Float.parseFloat(bageScore);
            log.info("Bage array = " + bageScores[i]);

        }
        assertTrue(bageScores.length > 0);
    }

//    @Step
//    public void searchShouldBePerformed(){
//        assertTrue(topPage.getCurrencySign().getAttribute("value").equals(currency));
//    }

}
