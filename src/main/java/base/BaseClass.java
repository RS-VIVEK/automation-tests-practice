package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.*;

import java.lang.reflect.Method;

@Listeners({listeners.ScreenshotListener.class})
public class BaseClass {

    private static final Logger logger = LogManager.getLogger(BaseClass.class);
    protected WebDriver driver;
    private DriverFactory driverFactory;
    protected ConfigReader configReader;

    @BeforeMethod
    public void setUp(@Optional("") String browser, Method method) {
        logger.info("===== Test Setup Started =====");

        configReader = new ConfigReader();
        driverFactory = new DriverFactory();
        driver = driverFactory.initializeDriver(configReader.getProperty("browser"));

        DriverManager.setDriver(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(configReader.getProperty("url"));

        String testName = method.getName();
        ExtentTestManager.createTest(testName);

        logger.info("Browser launched: " + configReader.getProperty("browser"));
        logger.info("Navigated to URL: " + configReader.getProperty("url"));
        logger.info("ExtentTest created for: " + testName);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("===== Test Teardown Started =====");
        if (driver != null) {
            try {
                driver.quit();
                logger.info("Browser closed successfully");
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
        logger.info("Extent Reports flushed");
    }
}
