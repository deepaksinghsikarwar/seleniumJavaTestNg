package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AmazonSearchPage extends BasePage {

    // Page Elements
    @FindBy(xpath = "//*[contains(@class,'nav-search-field ')]//input") // Locator for the search input field
    private WebElement searchBox;

    @FindBy(xpath = "//*[contains(@id,'nav-search-submit-button')]") // Locator for the Google Search button
    private WebElement searchButton;

    @FindBy(id = "result-stats") // Locator for the search results stats (optional)
    private WebElement resultsStats;

    @FindBy(xpath = "//*[@data-component-type='s-result-info-bar']") // Locator for search result titles
    private List<WebElement> searchResults;

    // Constructor
    public AmazonSearchPage(WebDriver driver) {
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
