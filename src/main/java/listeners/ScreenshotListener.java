package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.DriverManager;
import util.ExtentTestManager;
import util.ScreenshotUtil;

import java.io.File;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = DriverManager.getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
        if (screenshotPath != null) {
            String relativePath = "screenshots/" + new File(screenshotPath).getName();
            ExtentTest test = ExtentTestManager.getTest();
            if (test != null) {
                test.fail("Test Failed: " + testName, MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            }
        } else {
            System.out.println("⚠️ Screenshot capture failed for test: " + testName);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = DriverManager.getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
        if (screenshotPath != null) {
            String relativePath = "screenshots/" + new File(screenshotPath).getName();
            ExtentTest test = ExtentTestManager.getTest();
            if (test != null) {
                test.pass("Test Passed: " + testName, MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            }
        } else {
            System.out.println("⚠️ Screenshot capture failed for test: " + testName);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = DriverManager.getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
        if (screenshotPath != null) {
            String relativePath = "screenshots/" + new File(screenshotPath).getName();
            ExtentTest test = ExtentTestManager.getTest();
            if (test != null) {
                test.skip("Test Skipped: " + testName, MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
            }
        } else {
            System.out.println("⚠️ Screenshot capture failed for test: " + testName);
        }
    }
}
