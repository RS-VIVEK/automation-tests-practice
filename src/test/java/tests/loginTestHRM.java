package tests;

import base.BaseClass;
import org.testng.annotations.Test;
import pages.LoginPageHRM;

public class loginTestHRM extends BaseClass {

    @Test
    public void testValidLogin() {
        LoginPageHRM loginPagehrm = new LoginPageHRM(driver, Integer.parseInt(configReader.getProperty("timeout")));
        loginPagehrm.enterEmail(configReader.getProperty("email"));
        loginPagehrm.enterPassword(configReader.getProperty("password"));
        loginPagehrm.loginButton();
    }
}
