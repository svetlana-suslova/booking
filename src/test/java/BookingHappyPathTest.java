import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
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

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.*;

@Log4j
public class BookingHappyPathTest extends BaseTest{
    private TopPage topPage = new TopPage();
    private SearchBoxPage searchBoxPage = new SearchBoxPage();
    private FilterPage filterPage = new FilterPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();

    private String destination = "Kiev";
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
    public void testSearchBox(){
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
        searchBoxPage.typeInAndSelectDestination(destination);
        searchBoxPage.selectLastDateOfCurrentMonth("September");
        searchBoxPage.bookingDetailsOpen();
        searchBoxPage.selectAdults("1");
        searchBoxPage.selectChildren("1");
        searchBoxPage.selectChildAge("5");
        searchBoxPage.selectRooms("2");
        searchBoxPage.enableBusinessPurpose();
        searchBoxPage.searchButtonClick();
        searchBoxPage.whatIsTheCheckInDate();
        searchBoxPage.whatIsTheCheckOutDate();

        searchBoxPage.selectedDestinationShouldBe(destination, visible);
        checkInMonthShouldBeApplied(currentMonthName);
        checkInDateShouldBeApplied(lastDateOfCurrentMonth);
        checkOutMonthShouldBeApplied(nextMonthName);
        checkOutDateShouldBeApplied("1");
        searchBoxPage.selectedAdultsShouldBe("1", visible);
        searchBoxPage.selectedChildrenShouldBe("1", visible);
        searchBoxPage.selectedChildAgeShouldBe("5", exist);
        searchBoxPage.selectedRoomsShouldBe("2", exist); //BUG found here!
        searchBoxPage.businessPurposeShouldBeEnabled(exist);
    }

    @Test
    public void testFiltering() {
        topPage.currencySelectorClick();
        topPage.euroSelect();
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
        searchBoxPage.typeInAndSelectDestination(destination);
        searchBoxPage.selectLastDateOfCurrentMonth("September");
        searchBoxPage.searchButtonClick();
        filterPage.selectBudgetOption("1");
        filterPage.selectBudgetOption("2");
        filterPage.selectBudgetOption("3");
        filterPage.selectBudgetOption("4");
        filterPage.selectReviewWonderful();
        filterPage.selectReviewVeryGood();
        filterPage.selectAvailableOnly();

        filterPage.budgetOptionShouldBeSelected("1","true", visible);
        filterPage.budgetOptionShouldBeSelected("2","true", visible);
        filterPage.budgetOptionShouldBeSelected("3","true", visible);
        filterPage.budgetOptionShouldBeSelected("4","true", visible);
        filterPage.reviewOptionShouldBeSelected("1","true", visible);
        filterPage.reviewOptionShouldBeSelected("2","true", visible);
        filterPage.availableOnlyShouldBeSelected("true", visible);
    }

    @Test
    public void testFoundResults(){
        topPage.currencySelectorClick();
        topPage.euroSelect();
        topPage.languageSelectorClick();
        topPage.americanEnlishSelect();
        searchBoxPage.typeInAndSelectDestination(destination);
        searchBoxPage.selectLastDateOfCurrentMonth("September");
        searchBoxPage.bookingDetailsOpen();
        searchBoxPage.selectAdults("1");
        searchBoxPage.selectChildren("1");
        searchBoxPage.selectChildAge("5");
        searchBoxPage.selectRooms("2");
        searchBoxPage.enableBusinessPurpose();
        searchBoxPage.searchButtonClick();
        filterPage.selectBudgetOption("1");
        filterPage.selectBudgetOption("2");
        filterPage.selectBudgetOption("3");
        filterPage.selectBudgetOption("4");
        filterPage.selectReviewWonderful();
        filterPage.selectReviewVeryGood();
        filterPage.selectAvailableOnly();
        searchResultsPage.loaderShouldBe(hidden);

        checkFoundResultsTitleContains(destination);
        checkVisibilityOfFoundResults();
        searchResultsPage.whatIsThePriceHolderText();
        allResultsWithCorrespondingPriceShouldBeFound();
        minimumOneResultsWithCorrespondingReviewShouldBeFound();
        priceHoldersShouldContainCorrectWording(priceWordingUS);
        priceHoldersShouldContainCorrectCurrency(currencySign);
    }


    @Step
    private void flagShouldBe(String flag){
        assertTrue(topPage.getLanguageFlag().getAttribute("src").contains(flag));
    }

    @Step
    private void currencyShouldBe(String currency){
        assertTrue(topPage.getCurrencySign().getAttribute("value").equals(currency));
    }

    @Step
    private void checkInMonthShouldBeApplied(String month){
        String actualCheckInMonth = searchBoxPage.getCheckInDate();
        assertTrue(actualCheckInMonth.contains(month));
    }

    @Step
    private void checkInDateShouldBeApplied(String date){
        String actualCheckInDate = searchBoxPage.getCheckInDate();
        assertTrue(actualCheckInDate.contains(date));
    }

    @Step
    private void checkOutMonthShouldBeApplied(String month){
        String actualCheckOutMonth = searchBoxPage.getCheckOutDate();
        assertTrue(actualCheckOutMonth.contains(month));
    }

    @Step
    private void checkOutDateShouldBeApplied(String date){
        String actualCheckOutDate = searchBoxPage.getCheckOutDate();
        assertTrue(actualCheckOutDate.contains(date));
    }

    @Step
    private void checkFoundResultsTitleContains(String city) {
        assertTrue(searchResultsPage.getResultsTitle().getText().contains(city));
    }

    @Step
    private void checkVisibilityOfFoundResults() {
        searchResultsPage.getHotelNames().get(0).shouldBe(visible);
        searchResultsPage.getHotelImages().get(0).shouldBe(visible);
    }

    @Step
    private void allResultsWithCorrespondingPriceShouldBeFound(){
        int priceCount = searchResultsPage.getPriceHolders().size();
        log.info("Total price holders found on page: " + priceCount);
        int[] prices = new int[priceCount];
        for (int i=0; i<priceCount; i++){
            String price = searchResultsPage.getPriceHolders().get(i).getText()
                    .substring(searchResultsPage.getPriceHolderMessage().lastIndexOf("€") + 2);
            prices[i] = Integer.parseInt(price);
        }
        for (int price : prices) {
            assertTrue(price < 205);
        }
    }

    @Step
    private void minimumOneResultsWithCorrespondingReviewShouldBeFound(){
        int bageCount = searchResultsPage.getReviewBages().size();
        log.info("Total review bages found on page: " + bageCount);
        float[] bageScores = new float[bageCount];
        for (int i=0; i<bageCount; i++){
            String bageScore = searchResultsPage.getReviewBages().get(i).getText();
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
    private void priceHoldersShouldContainCorrectWording(String text) {
        assertTrue(searchResultsPage.getPriceHolderMessage().contains(text));
    }

    @Step
    private void priceHoldersShouldContainCorrectCurrency(String currency) {
        assertTrue(searchResultsPage.getPriceHolderMessage().contains(currency));
    }

    private void getCurrentMonthName(){
        Formatter f = new Formatter(Locale.ENGLISH);
        Calendar cal=Calendar.getInstance();
        currentMonthName = f.format("%tb", cal).toString();
        log.info("Current month: " + currentMonthName);
    }

    private void getNextMonthName(){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MM", Locale.JAPAN);
        String currentMonth_name = month_date.format(cal.getTime());
        cal.set(Calendar.MONTH, Integer.parseInt(currentMonth_name) - 1 + 1);
        Formatter f = new Formatter(Locale.ENGLISH);
        nextMonthName = f.format("%tb", cal).toString();
        log.info("Next month: " + nextMonthName);
    }

    private void getLastDateOfCurrentMonth(){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("dd");
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date lastDayOfMonth = cal.getTime();
        lastDateOfCurrentMonth = month_date.format(lastDayOfMonth);
        log.info("Last date of current month: " + lastDateOfCurrentMonth);
    }
}
