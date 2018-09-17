package utils.logging;

import com.codeborne.selenide.logevents.SimpleReport;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.ITestResult;

public class SelenideAllureListener extends ScreenShooter {

    protected SimpleReport simpleReport = new SimpleReport();

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        simpleReport.start();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        simpleReport.finish(result.getName());
        simpleReport.clean();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        SelenideScreenshot.screenshot();
        simpleReport.finish(result.getName());
        simpleReport.clean();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        super.onTestFailedButWithinSuccessPercentage(result);
    }
}
