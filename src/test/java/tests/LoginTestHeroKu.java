package tests;

import base.BaseClassAK;
import org.testng.annotations.Test;
import pages.LoginHeroKu;
import util.LogHelper;

import java.time.Duration;

public class LoginTestHeroKu extends BaseClassAK {

    @Test
    public void TestLogin() {
        LogHelper.info("===== Starting Login Test =====");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        LoginHeroKu loginHeroKu = new LoginHeroKu(driver, Integer.parseInt(configReader.getProperty("timeout")));
        LogHelper.info("Entering first name");
        loginHeroKu.enterFirstName(configReader.getProperty("firstname"));
        LogHelper.info("Entering Last name");
        loginHeroKu.enterLastName(configReader.getProperty("lastname"));
        LogHelper.info("Entering email address");
        loginHeroKu.enterEmailnew(configReader.getProperty("email1"));
       // loginHeroKu.enterNumber(configReader.getProperty("number"));
        LogHelper.info("Entering company");
        loginHeroKu.entercompany(configReader.getProperty("company"));
        LogHelper.info("Entering region");
        loginHeroKu.entercountry(configReader.getProperty("region"));
        loginHeroKu.sendAMessageToThem(configReader.getProperty("sendmessage"));
        LogHelper.info("Signup successful");
        LogHelper.info("===== Login Test Completed =====");
    }
}
