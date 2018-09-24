package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by bonialvirto on 9/20/18.
 */
@Log4j
@Getter
public class FilterPage {
//    private SelenideElement eur0_50 = $(By.xpath("//div[@id='filter_price']/div[2]/a[1]"));
//    private SelenideElement eur50_100 = $(By.xpath("//div[@id='filter_price']/div[2]/a[2]"));
//    private SelenideElement eur100_150 = $(By.xpath("//div[@id='filter_price']/div[2]/a[3]"));
//    private SelenideElement eur150_200 = $(By.xpath("//div[@id='filter_price']/div[2]/a[4]"));
    private SelenideElement reviewWonderful = $(By.xpath("//div[@id='filter_review']/div[2]/a[1]"));
    private SelenideElement reviewVeryGood = $(By.xpath("//div[@id='filter_review']/div[2]/a[2]"));
    private SelenideElement availableOnly = $(By.xpath("//div[@id='filter_out_of_stock']/div[2]/a"));

    @Step
    public void selectBudgetOption(String option) {
        Selenide.$(By.xpath("//div[@id='filter_price']/div[2]/a[" + option + "]")).click();
    }

    @Step
    public void selectReviewWonderful() {
        reviewWonderful.click();
    }

    @Step
    public void selectReviewVeryGood() {
        reviewVeryGood.click();
    }

    @Step
    public void selectAvailableOnly() {
        availableOnly.click();
    }

    @Step
    public void budgetOptionShouldBeSelected(String option, String status, Condition condition) {
        Selenide.$(By.xpath("//div[@id='filter_price']/div[2]/a[" + option + "][@aria-checked='" + status + "']")).shouldBe(condition);
    }

    @Step
    public void reviewOptionShouldBeSelected(String option, String status, Condition condition) {
        Selenide.$(By.xpath("//div[@id='filter_review']/div[2]/a[" + option + "][@aria-checked='" + status + "']")).shouldBe(condition);
    }

    @Step
    public void availableOnlyShouldBeSelected(String status, Condition condition) {
        Selenide.$(By.xpath("//div[@id='filter_out_of_stock']/div[2]/a[@aria-checked='" + status + "']")).shouldBe(condition);
    }
}
