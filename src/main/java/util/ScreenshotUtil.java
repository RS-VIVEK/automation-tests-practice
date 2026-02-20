package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) {
            System.out.println("⚠️ Driver is null, cannot capture screenshot");
            return null;
        }

        try {
            // Timestamp for unique file names
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Relative path (important for ExtentReports embedding)
            String relativePath = "screenshots/" + screenshotName + "_" + timeStamp + ".png";

            // Save inside reports/screenshots folder
            File finalDest = new File(System.getProperty("user.dir") + File.separator + "reports", relativePath);

            // Ensure directory exists
            File dir = finalDest.getParentFile();
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println("⚠️ Failed to create screenshot directory: " + dir.getAbsolutePath());
                return null;
            }

            // Capture screenshot
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, finalDest);

            System.out.println("✅ Screenshot saved at: " + finalDest.getAbsolutePath());

            // Return relative path so ExtentReports can embed it
            return relativePath;
        } catch (Exception e) {
            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }
}
