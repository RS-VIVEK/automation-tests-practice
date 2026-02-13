package util;

import org.testng.asserts.SoftAssert;

public class SoftAssertUtil {

    private static SoftAssert softAssertInstance;

    private SoftAssertUtil() {
    }

    public static SoftAssert getInstance() {
        if (softAssertInstance == null) {
            softAssertInstance = new SoftAssert();
        }
        return softAssertInstance;
    }


    public static void assertTrue(boolean condition, String message) {
        try {
            getInstance().assertTrue(condition, message);
        } catch (AssertionError e) {
            getInstance().fail(message);
        }
    }

    public static void assertEqual(Object actual, Object expected, String message) {
        try {
            getInstance().assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            getInstance().fail(message);
        }
    }

    public static void assertNotEqual(Object actual, Object expected, String message) {
        try {
            getInstance().assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            getInstance().fail(message);
        }
    }

    public static void assertAll() {
        getInstance().assertAll();
    }
}