package tests;

import base.BaseClassAK;
import org.testng.annotations.Test;
import pages.LoginHeroKu;
import java.time.Duration;

public class loginTestHeroKu extends BaseClassAK {

    @Test
    public void TestLogin() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        LoginHeroKu loginHeroKu = new LoginHeroKu(driver, Integer.parseInt(configReader.getProperty("timeout")));
        loginHeroKu.enterFirstName(configReader.getProperty("firstname"));
        loginHeroKu.enterLastName(configReader.getProperty("Lastname"));
        loginHeroKu.enterEmailnew(configReader.getProperty("email1"));
       // loginHeroKu.enterNumber(configReader.getProperty("number"));
        loginHeroKu.entercompany(configReader.getProperty("company"));
        loginHeroKu.entercountry(configReader.getProperty("Region"));
        loginHeroKu.sendAMessageToThem(configReader.getProperty("sendmessage"));
    }
}
