package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.DriverManager;
import util.ExtentTestManager;
import util.ScreenshotUtil;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        capture(result, "fail");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        capture(result, "pass");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        capture(result, "skip");
    }

    private void capture(ITestResult result, String status) {
        String testName = result.getName();
        WebDriver driver = DriverManager.getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
        ExtentTest test = ExtentTestManager.getTest();

        if (test != null && screenshotPath != null) {
            switch (status) {
                case "fail":
                    test.fail("Test Failed: " + testName,
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    break;
                case "pass":
                    test.pass("Test Passed: " + testName,
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    break;
                case "skip":
                    test.skip("Test Skipped: " + testName,
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    break;
            }
        }
    }
}
