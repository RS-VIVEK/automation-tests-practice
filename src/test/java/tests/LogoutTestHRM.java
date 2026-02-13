package tests;

import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.annotations.Test;
import pages.LogoutHRM;
import util.ExtentTestManager;
import util.ScreenshotUtil;

import java.io.File;

public class LogoutTestHRM extends BaseClass {

    @Test
    public void testValidLogoutHRM() {
        ExtentTest test = ExtentTestManager.createTest("Test Valid Logout HRM");

        LogoutHRM logoutHRM = new LogoutHRM(driver, Integer.parseInt(configReader.getProperty("timeout")));

        test.info("Entering the email address");
        logoutHRM.enterEmail(configReader.getProperty("email"));

        test.info("Entering the password");
        logoutHRM.enterPassword(configReader.getProperty("password"));

        test.info("Clicking the login button");
        logoutHRM.loginButton();

        test.info("Clicking the profile tab");
        logoutHRM.profileTabBtn();

        test.info("Clicking the signout tab");
        logoutHRM.signoutTab();

        // Capture screenshot and embed with MediaEntityBuilder
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "LogoutTestHRM");
        String relativePath = "./screenshots/" + new File(screenshotPath).getName();
        test.pass("Logout Successful",
                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
    }
}
