package tests;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPageHRM;
import util.ExtentTestManager;
import util.ScreenshotUtil;

import java.io.File;
import java.time.Duration;

public class LoginTestHRM extends BaseClass {

    private static final Logger logger = LogManager.getLogger(LoginTestHRM.class);

    @Test
    public void testValidLogin() {
        ExtentTest test = ExtentTestManager.createTest("Test Valid login data");
        logger.info("===== Starting Login Test =====");

        LoginPageHRM loginPagehrm = new LoginPageHRM(driver, Integer.parseInt(configReader.getProperty("timeout")));
        test.info("Entering the email address");
        logger.info("Entering email: " + configReader.getProperty("email"));
        loginPagehrm.enterEmail(configReader.getProperty("email"));

        test.info("Entering the password");
        logger.info("Entering password");
        loginPagehrm.enterPassword(configReader.getProperty("password"));

        test.info("Clicking the login button");
        logger.info("Clicking login button");
        loginPagehrm.loginButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement timeAtWork = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'oxd-text--p') and text()='Time at Work']")));
        try {
            Assert.assertEquals(timeAtWork.getText(), "Time at Work", "Login Failed - Time At Work not displayed");
            logger.info("Login successful, Time At Work verified");
        } catch (AssertionError e) {
            logger.error("Login failed - Time At Work not displayed", e);
            throw e;
        }

        // Capture screenshot and embed with MediaEntityBuilder
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "LoginTestHRM");
        String relativePath = "./screenshots" + new File(screenshotPath).getName();
        test.pass("Login Successful, HomePage Opened",
                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
        logger.info("===== Login Test Completed =====");
    }
}
