package util;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {

    private static final Logger logger = LogManager.getLogger(LogHelper.class);

    public static void info(String message){
        logger.info(message); //goes to Log file
        ExtentTest test = ExtentTestManager.getTest();
        if (test!=null){
            test.log(Status.INFO, message); // goes to Extent report
        }
    }

    public static void fail(String message){
        logger.error(message);
        ExtentTest test = ExtentTestManager.getTest();
        if (test!=null){
            test.log(Status.FAIL, message);
        }
    }

    public static void warn(String message){
        logger.warn(message);
        ExtentTest test = ExtentTestManager.getTest();
        if (test!=null){
            test.log(Status.WARNING, message);
        }
    }
}
