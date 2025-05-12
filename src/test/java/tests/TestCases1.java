package tests;

import common.BrowserFactory;
import org.testng.annotations.Test;
import pages.AmazonSearchPage;

public class TestCases1 extends BaseTest {

    AmazonSearchPage page;

    public TestCases1(){
        driver = BrowserFactory.getDriver();
        page = new AmazonSearchPage(driver);
    }

    @Test
    public void amazonSearch(){
        page.enterSearchQuery("searchTerm");
        page.submitSearchButton();
        page.clickSearchButton();
    }
}
