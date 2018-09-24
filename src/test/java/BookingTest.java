import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void clearData(){
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
        openWebSite();
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
    public void testSearchBoxSubmission(){
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
        searchBoxPage.typeInAndSelectDestination("Kiev");
        searchBoxPage.selectLastDateOfCurrentMonth("September");
        searchBoxPage.bookingDetailsBlockOpen();
        searchBoxPage.selectAdults("1");
        searchBoxPage.selectChildren("1");
        searchBoxPage.selectChildAge("5");
        searchBoxPage.selectRooms("2");
        searchBoxPage.enableBusinessPurpose();
        searchBoxPage.submitSearch();
        searchBoxPage.whatIsTheCheckInDate();
        searchBoxPage.whatIsTheCheckOutDate();
        searchBoxPage.selectedDestinationShouldBe("Kiev", Condition.visible);
        checkInMonthShouldBeApplied(currentMonthName);
        checkInDateShouldBeApplied(lastDateOfCurrentMonth);
        checkOutMonthShouldBeApplied(nextMonthName);
        checkOutDateShouldBeApplied("1");
        adultsSelectionShouldBe("1");
        childrenSelectionShouldBe("1");
        childAgeSelectionShould("5", Condition.exist);
        roomsSelectionShould("2", Condition.exist);
        searchBoxPage.businessPurposeShouldBeEnabled(Condition.exist);
    }

    @Test
    public void testFiltering() {
        topPage.currencySelectorClick();
        topPage.euroSelect();
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
        searchBoxPage.typeInAndSelectDestination("Kiev");
        searchBoxPage.selectLastDateOfCurrentMonth("September");
        searchBoxPage.submitSearch();
        filterPage.selectBudgetOption("1");
        filterPage.selectBudgetOption("2");
        filterPage.selectBudgetOption("3");
        filterPage.selectBudgetOption("4");
        filterPage.selectReviewWonderful();
        filterPage.selectReviewVeryGood();
        filterPage.selectAvailableOnly();
        filterPage.budgetOptionShouldBeSelected("1","true", Condition.visible);
        filterPage.budgetOptionShouldBeSelected("2","true", Condition.visible);
        filterPage.budgetOptionShouldBeSelected("3","true", Condition.visible);
        filterPage.budgetOptionShouldBeSelected("4","true", Condition.visible);
        filterPage.reviewOptionShouldBeSelected("1","true", Condition.visible);
        filterPage.reviewOptionShouldBeSelected("2","true", Condition.visible);
        filterPage.availableOnlyShouldBeSelected("true", Condition.visible);
    }

    @Test
    public void testDestinationSelector(){
        topPage.currencySelectorClick();
        topPage.euroSelect();
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
        searchBoxPage.typeInAndSelectDestination("Kiev");
        searchBoxPage.selectLastDateOfCurrentMonth("September");
        searchBoxPage.bookingDetailsBlockOpen();
        searchBoxPage.selectAdults("1");
        searchBoxPage.selectChildren("1");
        searchBoxPage.selectChildAge("5");
        searchBoxPage.selectRooms("2");
        searchBoxPage.enableBusinessPurpose();
        searchBoxPage.submitSearch();
        filterPage.selectBudgetOption("1");
        filterPage.selectBudgetOption("2");
        filterPage.selectBudgetOption("3");
        filterPage.selectBudgetOption("4");
        filterPage.selectReviewWonderful();
        filterPage.selectReviewVeryGood();
        filterPage.selectAvailableOnly();
        searchResultsPage.loaderShouldBe(Condition.hidden);

        resultsWithReviewShouldBeFound();
        searchResultsPage.whatIsThePriceHolderText();
        priceHoldersShouldContainCorrectWording(priceWordingUS);
        priceHoldersShouldContainCorrectCurrency(currencySign);
        searchResultsPage.resultsWithPriceShouldBeFound();
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

    @Step //to the page !!HashMap for search Box
    public void adultsSelectionShouldBe(String adult){
        String actualAdult = Selenide.$(By.xpath("//select[@id='group_adults']/option[@value='" + adult + "' and @selected='selected'] " +
                "| //label[@id='xp__guests__toggle']/span/span[1]")).getText();
        assertTrue(actualAdult.contains(adult));
    }

    @Step //to the page !!HashMap for search Box
    public void childrenSelectionShouldBe(String child){
        String actualChild = Selenide.$(By.xpath("//select[@id='group_children']/option[@value='" + child + "' and @selected='selected'] " +
                "| //label[@id='xp__guests__toggle']//span[2]/span")).getText();
        assertTrue(actualChild.contains(child));
    }

    @Step //to the page !!HashMap for search Box
    public void childAgeSelectionShould(String age, Condition condition){
        Selenide.$(By.xpath("//select[@name='age']/option[@value='" + age + "' and @selected='selected']"))
                .shouldBe(condition);
    }

    @Step //to the page !!HashMap for search Box
    public void roomsSelectionShould(String room, Condition condition){
        Selenide.$(By.xpath("//select[@id='no_rooms']/option[@value='" + room + "' and @selected='selected']"))
                .shouldBe(condition);
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


}
