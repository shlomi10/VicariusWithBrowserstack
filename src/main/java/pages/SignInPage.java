package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

import java.util.List;

/**
 * this class represents the signIn page
 *
 * @author Shlomi
 */

public class SignInPage extends BasePageFunctions {

    // constructor
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    By featuresTextBox = By.xpath("//ul[@class='features']//li");
    By loginBTN = By.xpath("//button[@class='btn btn-primary']");
    By emailFieldToWait = By.xpath("//input[@placeholder='Work e-mail']");
    By emailField = By.cssSelector(".input-text");
    String fakeMail = "abcd@gmail.com";
    By errorPopup = By.cssSelector(".notification-wrapper");
    By errorTextElement = By.xpath("//div[@class='notification-inner']//*");
    By forgotMyEmailBTN = By.cssSelector(".forgot");

    // validate we are on the right page
    public Boolean validateLoginPage() {
        waitForElementToBeVisible(emailFieldToWait);
        return getTextFromElement(loginBTN).equalsIgnoreCase("login");
    }

    // validate we are on the right page
    public Boolean validateNegativeMail() {
        return clearAndTypeTextToElem(emailField, fakeMail);
    }

    // click login
    public Boolean clickLogin() {
        return clickOnElement(loginBTN);
    }

    // validate error popup appear
    public Boolean validateError() {
        return waitForElementToBeVisible(errorPopup);
    }

    // validate error text
    public Boolean validateErrorText() {
        List<String> actualErrorText = getTextFromElements(errorTextElement);
        for (int i = 0; i < actualErrorText.size(); i++) {
            if (!actualErrorText.get(i).equalsIgnoreCase(Constants.expectedErrorText.get(i))) {
                System.out.println("Expected error text is not the same as the actual");
                return false;
            }
        }
        return true;
    }

    // validate features text
    public Boolean validateFeaturesText() {
        List<String> actualFeatures = getTextFromElements(featuresTextBox);
        for (int i = 0; i < actualFeatures.size(); i++) {
            if (!actualFeatures.get(i).equalsIgnoreCase(Constants.expectedFeatures.get(i))) {
                System.out.println("Expected features text is not the same as the actual");
                return false;
            }
        }
        return true;
    }

    // click forgot my email
    public Boolean clickForgotMyEmail() {
        return clickOnElement(forgotMyEmailBTN);
    }

    // validate no forgot my email button appear
    public Boolean validateNoForgotMyEmail() {
        return waitForElementToBeInvisible(forgotMyEmailBTN);
    }

}
