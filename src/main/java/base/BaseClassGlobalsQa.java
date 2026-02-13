package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.ConfigReaderGlobalsQA;
import util.DriverFactory;

public class BaseClassGlobalsQa {
    protected WebDriver driver;
    private DriverFactory driverFactory;
    protected ConfigReaderGlobalsQA configReader;

    @BeforeMethod
    public void setUp() {
        configReader = new ConfigReaderGlobalsQA();
        driverFactory = new DriverFactory();
        driver = driverFactory.initializeDriver(configReader.getProperty("browser2"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(configReader.getProperty("url3"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
