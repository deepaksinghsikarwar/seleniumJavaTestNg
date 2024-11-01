package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementUtils;

public class UtilityManager {

    private WebDriver driver; // Store the WebDriver instance

    // Constructor to initialize the UtilityManager with a WebDriver
    public UtilityManager(WebDriver driver) {
        this.driver = driver;
    }

    // Get WaitUtils instance, using the stored driver

    public ElementUtils getElementUtils(WebElement element) {
        return new ElementUtils(element, driver); // Pass the driver to ActionUtils
    }
}