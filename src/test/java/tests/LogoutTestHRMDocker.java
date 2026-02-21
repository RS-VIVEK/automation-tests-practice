package tests;

import base.DockerBaseClass;
import org.testng.annotations.Test;
import pages.LogoutHRM;
import util.LogHelper;

public class LogoutTestHRMDocker extends DockerBaseClass {

    @Test(description = "Valid logout from HRM application")
    public void testValidLogoutHRM() {

        LogHelper.info("====== Starting Docker Logout Test ======");

        LogoutHRM logoutHRM = new LogoutHRM(driver, Integer.parseInt(configReader.getProperty("timeout")));

        LogHelper.info("Entering email");
        logoutHRM.enterEmail(configReader.getProperty("email"));

        LogHelper.info("Entering password.");
        logoutHRM.enterPassword(configReader.getProperty("password"));

        LogHelper.info("Clicking login button");
        logoutHRM.loginButton();

        LogHelper.info("Clicking profile tab");
        logoutHRM.profileTabBtn();

        LogHelper.info("Clicking signout tab");
        logoutHRM.signoutTab();

        LogHelper.info("Logout successful, user signed out");
        LogHelper.info("===== Logout Test Completed (Docker Grid) =====");
        // Screenshots handled automatically by ScreenshotListener
    }
}
