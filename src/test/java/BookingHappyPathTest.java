import com.codeborne.selenide.Condition;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.TopPage;



public class BookingHappyPathTest extends BaseTest{
    private TopPage topPage = new TopPage();

    @BeforeClass
    public void prepareForeTest(){
        openWebSite();
        topPage.closeAlert();
    }

    @Test
    public void testCurrencySelector(){
        topPage.languageSelectorShouldBe(Condition.visible);
        topPage.languageSelectorClick();
    }
}
