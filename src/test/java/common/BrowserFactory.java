package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import common.interfaces.IBrowser;

public class BrowserFactory {

    // ThreadLocal for WebDriver instances to ensure thread safety in parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get driver for the current thread. If none exists, create one.
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver("chrome"));
        }
        return driver.get();
    }

    public static WebDriver getDriver(String browserName) {
        if (driver.get() == null) {
            driver.set(createDriver("chrome"));
        }
        return driver.get();
    }

    // Get a new driver instance for the current thread (ignores the ThreadLocal check)
    public static WebDriver getNewDriver(String browserType) {
        return createDriver(browserType); // Create and return a new WebDriver instance
    }

    // Private method to create a browser driver instance
    private static WebDriver createDriver(String browserType) {
        WebDriver newDriver;
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                // Check if running in CI environment
                String runEnv = System.getProperty("runEnv", System.getenv("RUN_ENV"));
                boolean isRemote = "ci".equalsIgnoreCase(runEnv);

                if (isRemote) {
                    // Apply only in non-local (remote) environments
                    String userDataDir = "/tmp/selenium_user_data_" + System.currentTimeMillis();
                    chromeOptions.addArguments("--user-data-dir=" + userDataDir);
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                }

                // Create and return Chrome driver with options
                newDriver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                newDriver = new FirefoxBrowser().createDriver();
                break;
            case "edge":
                newDriver = new EdgeBrowser().createDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser type " + browserType + " is not supported");
        }
        return newDriver;
    }

    // Quit the WebDriver for the current thread
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Remove the WebDriver instance from the current thread
        }
    }

    // Chrome browser implementation
    static class ChromeBrowser implements IBrowser {
        @Override
        public WebDriver createDriver() {
            return new ChromeDriver();
        }
    }

    // Firefox browser implementation
    static class FirefoxBrowser implements IBrowser {
        @Override
        public WebDriver createDriver() {
            return new FirefoxDriver();
        }
    }

    // Edge browser implementation
    static class EdgeBrowser implements IBrowser {
        @Override
        public WebDriver createDriver() {
            return new EdgeDriver();
        }
    }
}