package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import common.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import testdata.Constants;
import utils.ReportUtils.ScreenshotUtils;

import java.time.Duration;

public class BaseTest {

    public static ExtentReports extent;

    public static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/extent-report_"+ ScreenshotUtils.formattedDateTime()+".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        extent.flush();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters(value="BrowserName")
    public void setupDriver (String browserName) {
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        BrowserFactory.quitDriver();
    }
}
