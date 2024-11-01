package tests;

import common.BrowserFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import pages.GoogleSearchPage;

public class TestCases1 extends BaseTest {

    GoogleSearchPage page;

    public TestCases1(){
        driver = BrowserFactory.getDriver();
        page = new GoogleSearchPage(driver);
    }

    @Test
    public void googleSearch(){
        page.enterSearchQuery("searchTerm");
        page.submitSearchButton();
        page.clickSearchButton();
    }
}
