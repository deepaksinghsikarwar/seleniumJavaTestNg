package utils.ReportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtils {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    // Initialize the report using ExtentSparkReporter
    public static void initializeReport(String reportPath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    // Start a test in the report
    public static void startTest(String testName) {
        extentTest = extentReports.createTest(testName);
    }

    // Log test info
    public static void logInfo(String message) {
        extentTest.log(Status.INFO, message);
    }

    // Log test pass
    public static void logPass(String message) {
        extentTest.log(Status.PASS, message);
    }

    // Log test fail
    public static void logFail(String message) {
        extentTest.log(Status.FAIL, message);
    }

    // Finalize the report
    public static void finalizeReport() {
        extentReports.flush();
    }
}