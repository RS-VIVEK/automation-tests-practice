package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.JavaScriptUtil;
import util.WaitHelper;

public class LoginHeroKu {


    WebDriver driver;
    WaitHelper wait;
    JavaScriptUtil jsu;

    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By email1 = By.id("email");
    private By phoneNumber = By.xpath("//input[@id='input_10_5']");
    private By company = By.id("company");
    private By country = By.id("self_declared_country");
    private By sendAMessage = By.id("signup-button");
//    private By sendYourInqBtn = By.xpath("//button[@id='gform_submit_button_10']");

    public LoginHeroKu(WebDriver driver1, int timeout) {
        driver = driver1;
        this.wait = new WaitHelper(driver1, timeout);
        jsu = new JavaScriptUtil(driver1);

    }

    public void enterFirstName(String fname) {
    /*    Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(company))
                .pause(Duration.ofSeconds(10))   // ✅ adds a visible delay
                .perform();*/

        /*Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(firstName))
                .pause(Duration.ofSeconds(5))   // ✅ adds a visible delay
                .perform();*/

        WebElement firstNameElement = driver.findElement(company);
        jsu.scrollToElement(firstNameElement);
        driver.findElement(firstName).sendKeys(fname);
//		wait.waitForVisibility(firstName).sendKeys(fname);
    }

    public void enterLastName(String Lname) {
        driver.findElement(lastName).sendKeys(Lname);
//		wait.waitForVisibility(lastName).sendKeys(Lname);
    }

    public void enterEmailnew(String email) {
        driver.findElement(email1).sendKeys(email);
//		wait.waitForVisibility(email1).sendKeys(email);
    }

    public void enterNumber(String fnumber) {
        driver.findElement(phoneNumber).sendKeys(fnumber);
//		wait.waitForVisibility(phoneNumber).sendKeys(fnumber);
    }

    public void entercompany(String comp) {
        driver.findElement(company).sendKeys(comp);
//		wait.waitForVisibility(company).sendKeys(comp);
    }

    public void entercountry(String countryName) {
        Select st = new Select(driver.findElement(country));
        st.selectByVisibleText(countryName);
    }

    public void sendAMessageToThem(String message) {
        driver.findElement(sendAMessage).sendKeys(message);
//		wait.waitForVisibility(sendAMessage).sendKeys(message);


    }

}
