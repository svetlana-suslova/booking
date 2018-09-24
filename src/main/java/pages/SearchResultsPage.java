package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
/**
 * Created by bonialvirto on 9/21/18.
 */
@Log4j
@Getter
public class SearchResultsPage {
    private SelenideElement resultsTitle = $(By.xpath("//div[@id='right']//h1"));
    private ElementsCollection hotelImages = $$(By.xpath("//div[contains(@id,'hotel')]/a/img"));
    private ElementsCollection hotelNames = $$(By.xpath("//div[contains(@id,'hotel')]/following-sibling::div//a[@class='hotel_name_link url']"));
    private ElementsCollection reviewBages = $$(By.xpath("//div[@id='hotellist_inner']//a[1]//span[@class='review-score-badge'] " +
            "| //div[@id='hotellist_inner']//div[@class='bui-review-score__badge']"));
    private ElementsCollection priceHolders = $$(By.xpath("//table[@class='featuredRooms sr_room_table sr-group_recommendation sr_rms_tbl_bdr']/following-sibling::div"));
    private String priceHolderMessage;

    @Step
    public void loaderShouldBe(Condition condition){
        Selenide.$(By.xpath("//div[@class='sr-usp-overlay__loading']")).waitUntil(condition, 6000);
    }

    public void whatIsThePriceHolderText() {
        priceHolderMessage = priceHolders.get(0).getText();
        log.info("Price holder message: " + priceHolderMessage);
    }
}
