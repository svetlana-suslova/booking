import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FilterPage;
import pages.SearchBoxPage;
import pages.SearchResultsPage;
import pages.TopPage;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.testng.Assert.*;

@Log4j
public class BookingTest extends BaseTest{
    private TopPage topPage = new TopPage();
    private SearchBoxPage searchBoxPage = new SearchBoxPage();
    private FilterPage filterPage = new FilterPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();


    private String currentMonthName;
    private String lastDateOfCurrentMonth;
    private String nextMonthName;
    private String priceWordingUS = "Price for 1 adult, 1 child for 1 night:";
    private String currencySign = "€";

    @BeforeClass
    public void prepareForeTest(){
        getCurrentMonthName();
        getLastDateOfCurrentMonth();
        getNextMonthName();
        openWebSite();
    }

    public void getCurrentMonthName(){
        Formatter f = new Formatter();
        Calendar cal=Calendar.getInstance();
        currentMonthName = f.format("%tb", cal).toString();
        log.info("Current month: " + currentMonthName);
    }

    public void getNextMonthName(){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MM");
        String currentMonth_name = month_date.format(cal.getTime());
        cal.set(Calendar.MONTH, Integer.parseInt(currentMonth_name) - 1 + 1);
        Formatter f = new Formatter();
        nextMonthName = f.format("%tb", cal).toString();
        log.info("Next month: " + nextMonthName);
    }

    public void getLastDateOfCurrentMonth(){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("dd");
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date lastDayOfMonth = cal.getTime();
        lastDateOfCurrentMonth = month_date.format(lastDayOfMonth);
        log.info("Last date of current month: " + lastDateOfCurrentMonth);
    }


    @Test
    public void testCurrencySelector(){
        topPage.currencySelectorClick();
        topPage.euroSelect();
        currencyShouldBe("EUR");
    }

    @Test
    public void testLanguageSelector(){
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
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
        searchBoxPage.selectLastDateOfCurrentMonth("September");
        searchBoxPage.bookingDetailsBlockOpen();
        searchBoxPage.selectAdults("1");
        searchBoxPage.selectChildren("1");
        searchBoxPage.selectChildAge("5");
        searchBoxPage.selectRoom("2");
        searchBoxPage.enableBusinessPurpose();
        searchBoxPage.searchButtonClick();
        filterPage.selectPriceOption1();
        filterPage.selectPriceOption2();
        filterPage.selectPriceOption3();
        filterPage.selectPriceOption4();
        filterPage.selectReveiwOption1();
        filterPage.selectReveiwOption2();
        filterPage.selectAvailabilityOnly();
        searchResultsPage.loaderShouldBe(Condition.hidden);
        searchBoxPage.selectedDestinationShouldBe("Kiev", Condition.visible);
        searchBoxPage.whatIsTheCheckInDate();
        searchBoxPage.whatIsTheCheckOutDate();
        checkInMonthShouldBeApplied(currentMonthName);
        checkInDateShouldBeApplied(lastDateOfCurrentMonth);
        checkOutMonthShouldBeApplied(nextMonthName);
        checkOutDateShouldBeApplied("1");
        resultsWithReviewShouldBeFound();
        searchResultsPage.whatIsThePriceHolderText();
        priceHoldersShouldContainCorrectWording(priceWordingUS);
        priceHoldersShouldContainCorrectCurrency(currencySign);
        searchResultsPage.resultsWithPriceShouldBeFound();
    }

//    @Test
//    public void testDestinationSelectorByENTER(){
//        topPage.currencySelectorClick();
//        topPage.euroSelect();
//        currencyShouldBe("EUR");
//        topPage.languageSelectorClick();
//        topPage.americanEnlishSelect();
//        flagShouldBe("us");
//        searchBoxPage.getDestinationInput().clear();
//        searchBoxPage.typeDestination("Kiev");
//        Selenide.getFocusedElement().sendKeys(Keys.ENTER);
//        searchBoxPage.smallSearchFormShouldBe(Condition.visible);
//        searchBoxPage.selectLastDateOfCurrentMonth();
//        searchBoxPage.selectAdults();
//        searchBoxPage.selectChildren();
//        searchBoxPage.selectChildAge();
//        searchBoxPage.selectRoom();
//        searchBoxPage.enableBusinessPurpose();
//        searchBoxPage.submitSearch();
//       // searchBoxPage.checkInFieldShouldBe(Condition.visible);
//       // checkInDateShouldbeApplied();
//
//    }


    @Step
    public void flagShouldBe(String flag){
        assertTrue(topPage.getLanguageFlag().getAttribute("src").contains(flag));
    }

    @Step
    public void currencyShouldBe(String currency){
        assertTrue(topPage.getCurrencySign().getAttribute("value").equals(currency));
    }


    @Step
    public void checkInMonthShouldBeApplied(String month){
        String actualCheckInMonth = searchBoxPage.getCheckInDate();
        assertTrue(actualCheckInMonth.contains(month));
    }

    @Step
    public void checkInDateShouldBeApplied(String date){
        String actualCheckInDate = searchBoxPage.getCheckInDate();
        assertTrue(actualCheckInDate.contains(date));
    }

    @Step
    public void checkOutMonthShouldBeApplied(String month){
        String actualCheckOutMonth = searchBoxPage.getCheckOutDate();
        assertTrue(actualCheckOutMonth.contains(month));
    }

    @Step
    public void checkOutDateShouldBeApplied(String date){
        String actualCheckOutDate = searchBoxPage.getCheckOutDate();
        assertTrue(actualCheckOutDate.contains(date));
    }



    @Step
    public void resultsWithReviewShouldBeFound(){
        int bageCount = searchResultsPage.getReviewScoreBages().size();
        log.info("Total review bages found on page 1: " + bageCount);
        float[] bageScores = new float[bageCount];
        for (int i=0; i<bageCount; i++){
            String bageScore = searchResultsPage.getReviewScoreBages().get(i).getText();
            bageScores[i] = Float.parseFloat(bageScore);
        }
        int result = 0;
        for (float bage : bageScores){
            if (bage > 8.1f){
                result++;
                log.info("Review bage higher than 8.1: " + bage);
                break;
            }
        }
        assertTrue(result > 0);
    }

    @Step
    public void priceHoldersShouldContainCorrectWording(String text) {
        assertTrue(searchResultsPage.getPriceHolderText().contains(text));
    }

    @Step
    public void priceHoldersShouldContainCorrectCurrency(String currency) {
        assertTrue(searchResultsPage.getPriceHolderText().contains(currency));
    }
}
