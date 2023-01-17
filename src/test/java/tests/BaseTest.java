package tests;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;
import org.testng.annotations.*;
import pages.MainPage;
import pages.ProductPage;
import pages.SignInPage;
import pages.SignUpPage;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

/**
 * this class represents the base of all tests
 * this will be before each test in the testNG xml
 *
 * @author Shlomi
 */

public class BaseTest implements ITestListener {

    WebDriver driver;
    SignInPage signInPage;
    SignUpPage signUpPage;
    ProductPage productPage;
    MainPage mainPage;

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void setup(String browser, ITestContext context) {

        MutableCapabilities capabilities = new MutableCapabilities();

        try {
            // load properties
            Properties props = new Properties();

            String propFileName = "config.properties";
            // get the config properties file
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

            if (Boolean.parseBoolean(props.getProperty("use.selenium.grid"))) {
                capabilities.setCapability("browserName", props.getProperty("selenium.grid.browser.name"));
                HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                browserstackOptions.put("os", props.getProperty("selenium.grid.os.name"));
                browserstackOptions.put("osVersion", props.getProperty("selenium.grid.os.version"));
                browserstackOptions.put("browserVersion", props.getProperty("selenium.grid.browser.version"));
                browserstackOptions.put("local", props.getProperty("isRunLocal"));
                browserstackOptions.put("seleniumVersion", props.getProperty("selenium.grid.seleniumVersion"));
                capabilities.setCapability("bstack:options", browserstackOptions);

                URL browserStackUrl = new URL("https://" + props.getProperty("browserstack.username") + ":" + props.getProperty("browserstack.accessKey") + props.get("selenium.grid.url"));
                driver = new RemoteWebDriver((browserStackUrl), capabilities);
            } else {
                driver = new ChromeDriver();
            }

        } catch (Exception e) {
            System.out.println("There was problem load the properties file");
        }

        // maximize the browser window
        driver.manage().window().maximize();

        // load pages
        mainPage = new MainPage(driver);
        signInPage = new SignInPage(driver);
        signUpPage = new SignUpPage(driver);
        productPage = new ProductPage(driver);

        // set context of webDriver
        context.setAttribute("driver", driver);

    }

    @AfterTest(alwaysRun = true)
    public void close() {
        driver.quit();

    }

}
