import com.codeborne.selenide.Condition;

import lombok.extern.log4j.Log4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.TopPage;

@Log4j
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
        topPage.languageEnlishUSClick();

    }
}
