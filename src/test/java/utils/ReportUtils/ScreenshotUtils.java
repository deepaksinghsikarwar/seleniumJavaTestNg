package utils.ReportUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);
        String screenshotName = "screenshot_" + formattedDateTime();

        String folderName = "screenshots";
        // Create the folder if it does not exist
        Path folderPath = Paths.get(folderName);
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Path screenshotPath = Paths.get(folderName, screenshotName + ".png");
        Files.write(screenshotPath, screenshotBytes);

        return screenshotPath.toString();
    }

    public static String formattedDateTime(){
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a custom format for the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        return currentDateTime.format(formatter);
    }
}
