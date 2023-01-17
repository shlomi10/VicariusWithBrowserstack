package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the main page
 *
 * @author Shlomi
 */

public class MainPage extends BasePageFunctions {

    // constructor
    public MainPage(WebDriver driver) {
        super(driver);
    }

    By loginBTN = By.cssSelector(".link-button.hidden-lg-down");

    // get main page
    public Boolean getMainPage(String site) {
        return navigateToURL(site);
    }

    // open login page
    public Boolean getLoginPage() {
        return clickOnElement(loginBTN);
    }

}
