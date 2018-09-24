package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
/**
 * Created by bonialvirto on 9/20/18.
 */

public class FilterPage {
    @Step
    public void selectBudgetOption(String option) {
        Selenide.$(By.xpath("//div[@id='filter_price']/div[2]/a[" + option + "]")).click();
    }

    @Step
    public void selectReviewWonderful() {
        Selenide.$(By.xpath("//div[@id='filter_review']/div[2]/a[1]")).click();
    }

    @Step
    public void selectReviewVeryGood() {
        Selenide.$(By.xpath("//div[@id='filter_review']/div[2]/a[2]")).click();
    }

    @Step
    public void selectAvailableOnly() {
        Selenide.$(By.xpath("//div[@id='filter_out_of_stock']/div[2]/a")).click();
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
