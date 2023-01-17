package pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * this class represents the signUp page
 *
 * @author Shlomi
 */

public class SignUpPage extends BasePageFunctions {

    // constructor
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    By startFreeTrialBTN = By.cssSelector(".btn.btn-primary.full-width");
    By continueBTN = By.cssSelector(".btn.btn-primary.full-width");
    By firstNameField = By.xpath("//input[@placeholder='First Name']");
    By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    By workEmailField = By.xpath("//input[@placeholder='Work e-mail']");
    By companyField = By.xpath("//input[@placeholder='Company']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By confirmPasswordField = By.xpath("//input[@placeholder='Confirm Password']");
    By confirmationPasswordPlaceHolder = By.xpath("//div[@class='input-field is-error']");
    By hoorayTextElement = By.xpath("//div[@class='notification-inner']//*");
    By hoorayPopup = By.cssSelector(".heading.mb-4");
    By errorField = By.xpath("//li[@class='notification-wrapper']");
    By errorHelp = By.xpath("//div[@class='password-help']//li");
    By closeNotificationBTN = By.cssSelector(".notification.type-validation>.notification-close");

    // fill positive details
    public Boolean fillPositiveDetails() {
        try {
            waitForElementToBeVisible(continueBTN);
            clearAndTypeTextToElem(firstNameField, Constants.expectedFirstName);
            clearAndTypeTextToElem(lastNameField, Constants.expectedLastname);
            clearAndTypeTextToElem(workEmailField, Constants.expectedWorkEmail);
            clearAndTypeTextToElem(companyField, Constants.expectedCompany);
            clickOnElement(continueBTN);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            clearAndTypeTextToElem(passwordField, Constants.expectedPassword);
            clearAndTypeTextToElem(confirmPasswordField, Constants.expectedConfirmPassword);
            return true;
        } catch (Exception e) {
            System.out.println("Fields were not filled properly");
            return false;
        }
    }

    // navigate to signUp page
    public Boolean navigateToSignUpPage(String site) {
        return navigateToURL(site);
    }

    // fill positive details without field
    public Boolean fillPositiveDetailsWithoutField(String missingField) {
        Constants.setMissingField(missingField);
        try {
            waitForElementToBeVisible(continueBTN);
            clearAndTypeTextToElem(firstNameField, Constants.expectedFirstName);
            clearAndTypeTextToElem(lastNameField, Constants.expectedLastname);
            clearAndTypeTextToElem(workEmailField, Constants.expectedWorkEmail);
            clearAndTypeTextToElem(companyField, Constants.expectedCompany);
            clickOnElement(continueBTN);
            return true;
        } catch (Exception e) {
            System.out.println("Fields were not filled properly");
            return false;
        }
    }

    // fill positive details without password field
    public Boolean fillPositiveDetailsWithoutPasswordField(String missingField) {
        Constants.setMissingField(missingField);
        try {
            waitForElementToBeVisible(continueBTN);
            clearAndTypeTextToElem(firstNameField, Constants.expectedFirstName);
            clearAndTypeTextToElem(lastNameField, Constants.expectedLastname);
            clearAndTypeTextToElem(workEmailField, Constants.expectedWorkEmail);
            clearAndTypeTextToElem(companyField, Constants.expectedCompany);
            clickOnElement(continueBTN);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            clearAndTypeTextToElem(passwordField, Constants.expectedPassword);
            clearAndTypeTextToElem(confirmPasswordField, Constants.expectedConfirmPassword);
            return true;
        } catch (Exception e) {
            System.out.println("Fields were not filled properly");
            return false;
        }
    }

    // get error from field
    public Boolean getErrorFromField() {
        waitForElementToBeVisible(errorField);
        return getTextFromElement(errorField).equalsIgnoreCase(Constants.errorMissingFieldText);
    }

    // get error from confirmation password field
    public Boolean getErrorFromConfirmationPasswordField() {
        return getWebElement(confirmationPasswordPlaceHolder) != null;
    }

    // click get start free trial
    public Boolean clickStartFreeTrial() {
        return clickOnElement(startFreeTrialBTN);
    }

    // click continue
    public Boolean clickContinue() {
        return clickOnElement(continueBTN);
    }

    // validate hooray text
    public Boolean validateHoorayText() {
        List<String> actualErrorText = getTextFromElements(hoorayTextElement);
        for (int i = 0; i < actualErrorText.size(); i++) {
            if (!actualErrorText.get(i).equalsIgnoreCase(Constants.expectedHoorayText.get(i))) {
                System.out.println("Expected hooray text is not the same as the actual");
                return false;
            }
        }
        return true;
    }

    // get error from field
    public Boolean getErrorField(String error) {
        waitForElementToBeVisible(errorField);
        waitForElementToBeClickableAndClickIt(closeNotificationBTN);
        List<String> errorsText = getTextFromElements(errorHelp);
        for (String s : errorsText) {
            if (s.equalsIgnoreCase(error)) {
                if (getElementWithColorRed(errorHelp)) {
                    return true;
                }
            }
        }
        return false;
    }

    // validate hooray popup appear
    public Boolean validateHooray() {
        return waitForElementToBeVisible(hoorayPopup);
    }

    // navigate to product page
    public Boolean getProductPage(String site) {
        return navigateToURL(site);
    }


}
