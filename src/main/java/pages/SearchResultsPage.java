package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by bonialvirto on 9/21/18.
 */
@Getter
public class SearchResultsPage {
    private SelenideElement searchResultsTable = $(By.xpath("//div[@id='search_results_table']"));
    private ElementsCollection reviewScoreBage = $$(By.xpath("//div[@id='hotellist_inner']//a[1]//span[@class='review-score-badge'] " +
            "| //div[@id='hotellist_inner']//div[@class='bui-review-score__badge']"));

    private SelenideElement loader = $(By.xpath("//div[@class='sr-usp-overlay__loading']"));


    @Step
    public void searchResultsTableShouldBe(Condition condition){
        searchResultsTable.shouldBe(condition);
    }

    @Step
    public void loaderShouldBe(Condition condition){
        loader.shouldBe(condition);
    }

//    @Step
//    public void reviewScoreBageShouldBe(Condition condition){
//        reviewScoreBage.get(0).shouldBe(condition);
//    }





}
