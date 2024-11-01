package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UiElementDebugger {

    public static void addElementDebugInfo(WebElement element, WebDriver driver, final String info, final String tooltip) {
        if (element != null) {
            Point point = ((Locatable) element).getCoordinates().inViewPort();
            int x = point.x;
            int y = point.y;
            System.out.println("hello x = " + x);
            ((JavascriptExecutor) driver).executeScript(
                    String.format("var node = document.getElementById('wdDebugInfo');"
                                    + "if (!node){"
                                    + "node = document.createElement('span');"
                                    + "node.id = 'wdDebugInfo';"
                                    + "node.style.position = 'fixed';"
                                    + "node.style.zIndex = '9999999';"
                                    + "node.style.color = '%s';"
                                    + "node.style.background = 'white';"
                                    + "node.style['font-weight'] = 'bold';"
                                    + "node.style['font-size'] = '10pt';"
                                    + "document.body.appendChild(node);}"
                                    + "node.innerHTML = arguments[2];"
                                    + "node.title = arguments[3];"
                                    + "node.style.display = 'block';"
                                    + "node.style.left = window.innerWidth < arguments[0] + node.offsetWidth ? (window.innerWidth - node.offsetWidth - 5) < 0 "
                                    + "? 0 + 'px': (window.innerWidth - node.offsetWidth - 5) + 'px' : arguments[0] + 'px';"
                                    + "node.style.top = arguments[1] - node.offsetHeight - 5 > 0 ? (arguments[1] - node.offsetHeight - 5) + 'px' "
                                    + ": (arguments[1] + arguments[4].offsetHeight + 5) + 'px';",
                            "#dd0000"),
                    x, y, info, tooltip, element);
        }
    }

    public void removeElementDebugInfo(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(
                "var node = document.getElementById('wdDebugInfo');"
                        + "if (node) {node.style.display = 'none'}");
    }

    public static void highlightElement(WebElement webElement, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("arguments[0].style.border='5px solid %s'",
                "#dd0000"), webElement);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            js.executeScript("arguments[0].style.border='';", webElement); // Clear the highlight
        }, 5, TimeUnit.SECONDS);

        // Shutdown the scheduler if you won't use it again
        scheduler.shutdown();
    }

    private static void unhighlightElement(WebElement webElement, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='';", webElement);
    }

    public void afterSetValue(WebElement element, Object... args) {
        if (element.isDisplayed()) {
            unhighlightElement(element, BrowserFactory.getDriver());
            removeElementDebugInfo(BrowserFactory.getDriver());
        }
    }
}
