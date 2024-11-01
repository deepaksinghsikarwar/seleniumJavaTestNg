package pages;

import common.UtilityManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    UtilityManager utilityManager;

    public BasePage(WebDriver driver){
        this.driver = driver;
        utilityManager = new UtilityManager(driver);
        PageFactory.initElements(driver,this);
    }
}
