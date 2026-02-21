package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DockerDriverFactory {

    public static WebDriver getDriver(String browser) throws MalformedURLException {
        String hubHost = System.getenv("HUB_HOST");
        if (hubHost == null) {
            hubHost = "localhost"; // fallback for local debugging
        }

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            return new RemoteWebDriver(new URL("http://" + hubHost + ":4444/wd/hub"), options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            return new RemoteWebDriver(new URL("http://" + hubHost + ":4444/wd/hub"), options);
        }
        throw new IllegalArgumentException("Browser not supported: " + browser);
    }
}
