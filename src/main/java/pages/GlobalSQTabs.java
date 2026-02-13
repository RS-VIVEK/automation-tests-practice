package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.WaitHelper;

import java.util.concurrent.TimeUnit;

public class GlobalSQTabs {

    WebDriver driver;
    WaitHelper wait;

    private By tab1 = By.xpath("//a[normalize-space()='Tabs']");
    private By iframeloc = By.className("demo-frame");
    private By simpleAccordionTab = By.id("Simple Accordion");
    private By section1Tab = By.id("ui-id-1");
    private By section1Content = By.id("ui-id-2");


    public GlobalSQTabs(WebDriver driver, int timeout){
        this.driver = driver;
        wait = new WaitHelper(driver, timeout);
    }

    public void openTabPage(){
        wait.waitForVisibility(tab1);
        driver.findElement(tab1).click();
    }

    public void clickSimpleAccordionTab(){
        wait.waitForVisibility(simpleAccordionTab);
        driver.findElement(simpleAccordionTab).click();
    }

    public void switchToIframe(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.waitForVisibility(iframeloc);
        driver.findElement(iframeloc).click();
    }

    public void switchBackToMainPage(){
        driver.switchTo().defaultContent();
    }

    public void clickSection1(){
        wait.waitForVisibility(iframeloc);
        driver.findElement(section1Tab).click();
    }
    public String getSectionText(){
        return driver.findElement(section1Content).getText();
    }
}
