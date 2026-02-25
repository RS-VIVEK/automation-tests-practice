package util;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {

    private ActionsUtil() {
    }
    // Hover over an element
    public static void hover(WebDriver driver, WebElement element){
        new Actions(driver).moveToElement(element).perform();
    }

    // Click on an element
    public static void click(WebDriver driver, WebElement element){
        new Actions(driver).moveToElement(element).click().perform();
    }

    // Double click
    public static void doubleClick(WebDriver driver, WebElement element){
        new Actions(driver).doubleClick(element).perform();
    }

    // Right click (context click)
    public static void rightClick(WebDriver driver, WebElement element){
        new Actions(driver).contextClick(element).perform();
    }

    // Drag and drop
    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target){
        new Actions(driver).dragAndDrop(source, target).perform();
    }

    // Send keys to element
    public static void senKeys(WebDriver driver, WebElement element, String text){
        new Actions(driver).moveToElement(element).click().sendKeys(text).perform();
    }

    // Press a single key (like ENTER, TAB, ESC)
    public static void pressKey(WebDriver driver, Keys keys){
        new Actions(driver).sendKeys(keys).perform();
    }

    // Press key combination (like CTRL + A, CTRL + C)
    public static void pressKeyCombination(WebDriver driver, Keys modifier, String keys){
        new Actions(driver).keyDown(modifier).sendKeys(keys).keyUp(modifier).perform();
    }
}

