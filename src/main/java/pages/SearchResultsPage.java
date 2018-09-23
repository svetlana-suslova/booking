package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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
    //private SelenideElement searchResultsTable = $(By.xpath("//div[@id='search_results_table']"));
    private ElementsCollection reviewScoreBages = $$(By.xpath("//div[@id='hotellist_inner']//a[1]//span[@class='review-score-badge'] " +
            "| //div[@id='hotellist_inner']//div[@class='bui-review-score__badge']"));
 //   private ElementsCollection priceHolders = $$(By.xpath("//div[@id='hotellist_inner']//div[@class='totalPrice totalPrice_no-rack-rate entire_row_clickable']"));
    //private ElementsCollection priceHolders = $$(By.xpath("//div[@id='hotellist_inner']/div/div[2]/div[2]/div/div[2]/div"));
    private ElementsCollection priceHolders = $$(By.xpath("//table[@class='featuredRooms sr_room_table sr-group_recommendation sr_rms_tbl_bdr']/following-sibling::div"));

    private SelenideElement loader = $(By.xpath("//div[@class='sr-usp-overlay__loading']"));
    private String priceHolderText;


//    @Step
//    public void searchResultsTableShouldBe(Condition condition){
//        searchResultsTable.shouldBe(condition);
//    }

    @Step
    public void loaderShouldBe(Condition condition){
        loader.shouldBe(condition);
    }



    @Step
    public void priceHoldersShouldBe(Condition condition){
        priceHolders.get(0).shouldBe(condition);
    }


    public void whatIsThePriceHolderText() {
        priceHolderText = priceHolders.get(0).getText();
        log.info("/" + priceHolderText + "/");
        log.info("/" + priceHolderText.substring(priceHolderText.lastIndexOf("€") + 2) + "/");
    }


    public void resultsWithPriceShouldBeFound(){
        int priceCount = priceHolders.size();
        log.info("Total price holder found on page 1: " + priceCount);
        int[] prices = new int[priceCount];
        for (int i=0; i<priceCount; i++){
            String price = priceHolders.get(i).getText().substring(priceHolderText.lastIndexOf("€") + 2);
            prices[i] = Integer.parseInt(price);
        }
       // int result = 0;
        for (int price : prices) {
            if (price < 205) {
                //    result++;
                log.info("Price is under €205: " + price);
               // break;
            }
        }
    }
}
