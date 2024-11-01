package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleSearchPage extends BasePage {

    // Page Elements
    @FindBy(name = "q") // Locator for the search input field
    private WebElement searchBox;

    @FindBy(name = "btnK") // Locator for the Google Search button
    private WebElement searchButton;

    @FindBy(id = "result-stats") // Locator for the search results stats (optional)
    private WebElement resultsStats;

    @FindBy(css = "h3") // Locator for search result titles
    private List<WebElement> searchResults;

    // Constructor
    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    // Method to enter a search query
    public void enterSearchQuery(String query) {
        utilityManager.getElementUtils(searchBox).inputText(query, true);
    }

    // Method to click the search button
    public void clickSearchButton() {
        utilityManager.getElementUtils(searchBox).click(false).inputText("text1", false);
    }

    public void submitSearchButton() {
        searchButton.submit();
    }
}