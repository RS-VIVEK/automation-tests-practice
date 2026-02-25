package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.ActionsUtil;
import util.DriverManager;

import java.util.Set;

public class TestingPractice {
    @BeforeMethod
    public void setUp() { // Initialize driver here for testing
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
    }

    @Test
    public void testPressKeys() throws InterruptedException {

        WebDriver driver = DriverManager.getDriver();
        driver.get("https://the-internet.herokuapp.com/key_presses");
        //  driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // WebElement hover = driver.findElement(By.id("mousehover"));
        // WebElement element = driver.findElement(By.xpath("//a[normalize-space()='Top']"));
        WebElement source = driver.findElement(By.id("target"));
        ActionsUtil.pressKey(driver, Keys.SHIFT);
        Thread.sleep(5000);
       ActionsUtil.senKeys(driver, source, "enter");
        Thread.sleep(5000);
        ActionsUtil.pressKey(driver, Keys.ENTER);
        Thread.sleep(5000);
        ActionsUtil.pressKeyCombination(driver, Keys.CONTROL, "C");
        Thread.sleep(5000);
        ActionsUtil.pressKey(driver, Keys.DELETE);
        Thread.sleep(5000);
        ActionsUtil.pressKeyCombination(driver, Keys.CONTROL, "v");

    }

    @Test
    public void testMultipleWindow() throws InterruptedException{
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://the-internet.herokuapp.com/windows");
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent window handle: "+parentWindow);
        System.out.println("Parent window title: "+driver.getTitle());

        //Opening new window
        driver.findElement(By.linkText("Click Here")).click();
        Thread.sleep(2000);

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows){
            if (!windowHandle.equals(parentWindow)){
                driver.switchTo().window(windowHandle);
                System.out.println("Child window handle : "+windowHandle);
                System.out.println("Child window tite: "+driver.getTitle());

                driver.close();
                System.out.println("Child window closed");
            }
        }
        driver.switchTo().window(parentWindow);
        System.out.println("Back to parent window");
        System.out.println("parent window title again: "+driver.getTitle());
    }

    @Test
    public void testNestedFrames() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // Step 1: Switch to top frame
        driver.switchTo().frame("frame-top");
        System.out.println("Switched to frame-top");

        // Step 2: Switch to left frame inside top
        driver.switchTo().frame("frame-left");
        String leftText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Left frame text: " + leftText);
        // Output: LEFT

        // Step 3: Go back to parent (frame-top)
        driver.switchTo().parentFrame();

        // Step 4: Switch to middle frame
        driver.switchTo().frame("frame-middle");
        String middleText = driver.findElement(By.id("content")).getText();
        System.out.println("Middle frame text: " + middleText);
        // Output: MIDDLE

        // Step 5: Back to parent again
        driver.switchTo().parentFrame();

        // Step 6: Switch to right frame
        driver.switchTo().frame("frame-right");
        String rightText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Right frame text: " + rightText);
        // Output: RIGHT

        // Step 7: Back to default content
        driver.switchTo().defaultContent();

        // Step 8: Switch to bottom frame
        driver.switchTo().frame("frame-bottom");
        String bottomText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Bottom frame text: " + bottomText);
        // Output: BOTTOM

        driver.switchTo().defaultContent(); // back to main page
    }

    @AfterMethod
    public void tearDown() { // Quit driver after test
        DriverManager.getDriver().quit();
        DriverManager.unload();
    }
}
