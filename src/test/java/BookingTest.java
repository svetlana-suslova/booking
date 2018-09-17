import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.TopPage;

import static org.testng.Assert.assertTrue;

@Log4j
public class BookingTest extends BaseTest{
    private TopPage topPage = new TopPage();

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


    @Step
    public void flagShouldBe(String flag){
        assertTrue(topPage.getLanguageFlag().getAttribute("src").contains(flag));
    }

    @Step
    public void currencyShouldBe(String currency){
        assertTrue(topPage.getCurrencySign().getAttribute("value").equals(currency));
    }

}
