package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // ✅ Save screenshots inside the reports/screenshots folder
        String dirPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "screenshots";
        File dir = new File(dirPath);

        if (!dir.exists()) {
            dir.mkdir();
        }

        String destination = dirPath + File.separator + screenshotName + "_" + timeStamp + ".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File finalDest = new File(destination);
            FileUtils.copyFile(source, finalDest);
            return destination;
        } catch (IOException e) {
            System.out.println("Screenshot Capture failed : " + e.getMessage());
            return null;
        }
    }
}
