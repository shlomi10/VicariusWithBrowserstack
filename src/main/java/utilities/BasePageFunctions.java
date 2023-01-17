package utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * this class represents the main functions of all pages
 *
 * @author Shlomi
 */


public abstract class BasePageFunctions {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // constructor
    public BasePageFunctions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // get webDriver
    public WebDriver getDriver() {
        return this.driver;
    }

    // navigate to URL
    public Boolean navigateToURL(String URL) {
        try {
            getDriver().navigate().to(URL);
            return true;
        } catch (Exception e) {
            System.out.println("Site was not loaded");
            return false;
        }
    }

    // get back webElement
    public WebElement getWebElement(By elem) {
        return getDriver().findElement(elem);
    }

    // click on element
    public Boolean clickOnElement(By elem) {
        try {
            getWebElement(elem).click();
            return true;
        } catch (Exception e) {
            System.out.println("Element " + elem + " was not clicked");
            return false;
        }
    }

    // clear field and then type text
    public Boolean clearAndTypeTextToElem(By elem, String text) {
        try {
            WebElement textField = getWebElement(elem);
            textField.clear();
            textField.sendKeys(text);
            return true;
        } catch (Exception e) {
            System.out.println("Element " + elem + " was not clear and text was not sent to");
            return false;
        }
    }

    // element to be clickable and click it
    public Boolean waitForElementToBeClickableAndClickIt(By elem) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elem)).click();
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be clickable was not worked correct");
            return false;
        }
    }

    // wait for element to be visible
    public Boolean waitForElementToBeVisible(By elem) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be visible was not worked correct");
            return false;
        }
    }

    // wait for element to be invisible
    public Boolean waitForElementToBeInvisible(By elem) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(elem));
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be invisible was not worked correct");
            return false;
        }
    }

    // get text from element
    public String getTextFromElement(By elem) {
        return getWebElement(elem).getText();
    }

    // get text of elements
    public List<String> getTextFromElements(By elem) {
        List<WebElement> elementList = getDriver().findElements(elem);
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elementList) {
            stringList.add(element.getText());
        }
        return stringList;
    }

    // get href of elements
    public List<String> getHrefFromElements(By elem) {
        List<WebElement> elementList = getDriver().findElements(elem);
        List<String> hrefList = new ArrayList<>();
        for (WebElement element : elementList) {
            hrefList.add(element.getAttribute("href"));
        }
        return hrefList;
    }

    // get element with color red
    public Boolean getElementWithColorRed(By elem) {
        List<WebElement> elementList = getDriver().findElements(elem);
        for (WebElement element : elementList) {
            if (element.getCssValue("background-color").equalsIgnoreCase("rgba(255, 104, 114, 0.15)")) {
                return true;
            }
        }
        return false;
    }

    //navigate to previous page
    public Boolean navigateToPreviousPage() {
        try {
            getDriver().navigate().back();
            return true;
        } catch (Exception e) {
            System.out.println("Navigation back wasn't succeeded");
            return false;
        }
    }

    // scroll to element
    public boolean scrollToElement(By elemToScroll) {
        try {
            WebElement element1 = getWebElement(elemToScroll);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element1);
            return true;
        } catch (Exception e) {
            System.out.println("Scroll to element was not worked correct");
            return false;
        }
    }

}
