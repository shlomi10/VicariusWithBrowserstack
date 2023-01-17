package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

import java.util.List;

/**
 * this class represents the product page
 *
 * @author Shlomi
 */

public class ProductPage extends BasePageFunctions {

    // constructor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    By loginLink = By.cssSelector(".link-button.hidden-lg-down");
    By topiaLogo = By.xpath("//div[@class='title size-lg']");
    By companyDescription = By.cssSelector(".text.pre-line.print-mr");
    By linkElements = By.xpath("//div[@class='link-group'] //a");

    // click login
    public Boolean clickLogin() {
        return waitForElementToBeClickableAndClickIt(loginLink);
    }

    // navigate back
    public Boolean navigateBack() {
        return navigateToPreviousPage();
    }

    // validate we are on the product page
    public Boolean validateProductPage() {
        return waitForElementToBeVisible(topiaLogo);
    }

    // scroll to description of the company
    public boolean validateCompanyDescription() {
        scrollToElement(companyDescription);
        List<String> actualErrorText = getTextFromElements(companyDescription);
        for (int i = 0; i < actualErrorText.size(); i++) {
            if (!actualErrorText.get(i).equalsIgnoreCase(Constants.expectedDescription.get(i))) {
                System.out.println("Expected description text is not the same as the actual");
                return false;
            }
        }
        return true;
    }

    // validate all links are not empty
    public boolean validateLinksAreNotEmpty() {
        scrollToElement(companyDescription);
        List<String> actualHrefText = getHrefFromElements(linkElements);
        for (int i = 0; i < actualHrefText.size(); i++) {
            if (!actualHrefText.get(i).equalsIgnoreCase(Constants.expectedLinks.get(i))) {
                System.out.println("Expected description text is not the same as the actual");
                System.out.println("Expected description text is : " + Constants.expectedLinks.get(i));
                System.out.println("Actual description text is : " + actualHrefText.get(i));
                return false;
            }
        }
        return true;
    }


}
