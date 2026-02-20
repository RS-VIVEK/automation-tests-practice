package base;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.*;

import java.lang.reflect.Method;


public class BaseClassAK {

    private static final Logger logger = LogManager.getLogger(BaseClassAK.class);
    protected WebDriver driver;
    private DriverFactory driverFactory;
    protected ConfigReaderHeroKu configReader;

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("======== Test Setup Started ========");

        configReader = new ConfigReaderHeroKu();
        driverFactory = new DriverFactory();
        driver = driverFactory.initializeDriver(configReader.getProperty("browser"));

        //Save Driver in DiverManager
        DriverManager.setDriver(driver);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(configReader.getProperty("url1"));

        //Create ExtentTest Automatically using test method name
        String testName = method.getName();
        ExtentTestManager.createTest(testName);

        logger.info("Browser launched: "+configReader.getProperty("browser1"));
        logger.info("Navigated to URL: "+configReader.getProperty("url1"));
        logger.info("ExtentTest create for: "+testName);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("===== Teardown Started =====");
        if (driver != null) {
            try {
            driver.quit();
            logger.info("Browser closed successfully");
        }catch (Exception e){
                logger.error("Browser didn't close properly", e);
            }finally {
                {
                    //Flush ExtentReports after each Test
                    ExtentManager.getInstance().flush();
                    logger.info("Extent Reports flushed");
                }
            }
            }
        // ✅ Flush ExtentReports after each test
        ExtentManager.getInstance().flush();
    }
}


