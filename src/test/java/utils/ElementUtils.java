package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.UiElementDebugger;

import java.time.Duration;

public class ElementUtils {

    private WebDriver driver; // Instance variable for WebDriver
    private WebElement element; // Current WebElement to operate on

    // Constructor to accept WebDriver
    public ElementUtils(WebElement element, WebDriver driver) {
        this.element = element;
        this.driver = driver;
    }

    // Click the specified element with optional wait for visibility
    public ElementUtils click(boolean waitForVisibility) {
        if (waitForVisibility) {
            waitForElementVisibility(30);
        }
        element.click();
        return this; // Return the current instance for chaining
    }

    // Hover over the specified element with optional wait for visibility
    public ElementUtils hover(boolean waitForVisibility) {
        if (waitForVisibility) {
            waitForElementVisibility(30);
        }
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return this; // Return the current instance for chaining
    }

    // Input text into the specified element with optional wait for visibility
    public ElementUtils inputText(String text, boolean waitForVisibility) {
        if (waitForVisibility) {
            waitForElementVisibility(30);
        }
        element.clear();
        element.sendKeys(text);
        return this; // Return the current instance for chaining
    }

    public void waitForElementVisibility(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            UiElementDebugger.highlightElement(element, driver);
        } catch (Exception ignored){
        }
    }

    // Other actions...
}