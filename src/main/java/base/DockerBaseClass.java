package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.*;

import java.lang.reflect.Method;

@Listeners({listeners.ScreenshotListener.class})
public class DockerBaseClass {

    private static final Logger logger = LogManager.getLogger(DockerBaseClass.class);
    protected WebDriver driver;
    protected ConfigReader configReader;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, Method method) throws Exception {
        logger.info("======== Docker Test Setup Started ========");

        configReader = new ConfigReader();

        // Prefer TestNG parameter, fallback to config file
        String browserToUse = browser.isEmpty() ? configReader.getProperty("browser") : browser;

        driver = DockerDriverFactory.getDriver(browserToUse);
        DriverManager.setDriver(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(configReader.getProperty("url"));

        String testName = method.getName();
        ExtentTestManager.createTest(testName);

        logger.info("Browser launched (Docker Grid): " + browserToUse);
        logger.info("Navigated to URL: " + configReader.getProperty("url"));
        logger.info("ExtentTest created for: " + testName);
    }


    @AfterMethod
    public void tearDown() {
        logger.info("======== Docker Test Teardown Started ========");
        if (driver != null) {
            try {
                driver.quit();
                logger.info("Browser closed successfully (Docker Grid)");
            } catch (Exception e) {
                logger.error("Browser did not close properly", e);
            } finally {
                DriverManager.unload();
                ExtentTestManager.unload();
            }
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        ExtentManager.getInstance().flush();
        logger.info("Extent Reports flushed (Docker Grid)");
    }
}
