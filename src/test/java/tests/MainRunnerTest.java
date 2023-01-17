package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Constants;

import java.io.InputStream;
import java.util.Properties;

/**
 * this class represents the test runner
 *
 * @author Shlomi
 */

public class MainRunnerTest extends BaseTest {

    private String siteURL;
    private String productURL;
    private String signInURL;
    private String signUpURL;

    @BeforeClass
    public void beforeMethod() {
        try {
            // load properties
            Properties props = new Properties();

            String propFileName = "config.properties";
            // get the config properties file
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            siteURL = props.getProperty("siteURL");
            productURL = props.getProperty("productPage");
            signUpURL = props.getProperty("signUpPage");
            signInURL = props.getProperty("signInPage");

        } catch (Exception e) {
            System.out.println("There was problem load the properties file");
        }
    }

    @Test(priority = 1, groups = "login page", description = "Validate sign In page test")
    public void openSignInPageTest() {
        mainPage.getMainPage(siteURL);
        Assert.assertTrue(mainPage.getLoginPage(), "Site was not loaded");
        Assert.assertTrue(signInPage.validateLoginPage(), "Login page was not open");
    }

    @Test(priority = 2, groups = "login page", description = "Sign in with invalid Mail and validate pop up appear")
    public void negativeTestLoginPopUp() {
        signInPage.validateNegativeMail();
        signInPage.clickLogin();
        Assert.assertTrue(signInPage.validateError(), "No error pop up appeared");
    }

    @Test(priority = 3, groups = "login page", description = "Sign in with invalid Mail and validate text appear")
    public void negativeTestLoginText() {
        signInPage.validateNegativeMail();
        signInPage.clickLogin();
        Assert.assertTrue(signInPage.validateErrorText(), "No text was at error text box");
    }

    @Test(priority = 4, groups = "login page", description = "Validate features text")
    public void validateFeatureTextTest() {
        Assert.assertTrue(signInPage.validateFeaturesText(), "No text was under features");
    }

    @Test(priority = 5, groups = "login page", description = "Validate forgot email link")
    public void validateForgotEmailLinkTest() {
        signInPage.clickForgotMyEmail();
        Assert.assertTrue(signInPage.validateNoForgotMyEmail(), "user was not moved to forgot my email page");
        // this test is meant to be failed since there is no forgot email page the user is moving to
    }

    @Test(priority = 6, groups = "signup page", description = "Sign up with valid data and validate pop up appear")
    public void positiveSignUpPopUpTest() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetails();
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.validateHooray(), "No hooray pop up appeared");
    }

    @Test(priority = 7, groups = "signup page", description = "Validate positive registration")
    public void positiveSignUpTest() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetails();
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.validateHoorayText(), "No text was at the hooray text box");
    }

    @Test(priority = 8, groups = "signup page", description = "Validate negative registration missing first name")
    public void negativeSignUpTestMissingFirstName() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutField("firstName");
        Assert.assertTrue(signUpPage.getErrorFromField(), "No error at missing field appeared");
    }

    @Test(priority = 9, groups = "signup page", description = "Validate negative registration missing last name")
    public void negativeSignUpTestMissingLastName() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutField("lastName");
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.getErrorFromField(), "No error at missing field appeared");
    }

    @Test(priority = 10, groups = "signup page", description = "Validate negative registration missing email")
    public void negativeSignUpTestMissingEmail() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutField("email");
        signUpPage.clickContinue();
        Assert.assertTrue(signUpPage.getErrorFromField(), "No error at missing field appeared");
    }

    @Test(priority = 11, groups = "signup page", description = "Validate negative registration missing company")
    public void negativeSignUpTestMissingCompany() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutField("company");
        signUpPage.clickContinue();
        Assert.assertTrue(signUpPage.getErrorFromField(), "No error at missing field appeared");
    }

    @Test(priority = 12, groups = "signup page", description = "Validate negative registration missing password")
    public void negativeSignUpTestMissingPassword() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutPasswordField("password");
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.getErrorFromField(), "No error at missing field appeared");
    }

    @Test(priority = 13, groups = "signup page", description = "Validate negative registration missing confirm password field")
    public void negativeSignUpTestMissingPasswordConfirmationField() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutPasswordField("confirmationPassword");
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.getErrorFromField(), "No error at missing confirmation field appeared");
    }

    @Test(priority = 14, groups = "signup page", description = "Validate negative registration with invalid email")
    public void negativeSignUpTestInvalidEmail() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutField("invalidEmail");
        Assert.assertTrue(signUpPage.getErrorFromField(), "There is no error text at email field");
    }

    @Test(priority = 15, groups = "signup page", description = "Validate negative registration with short email")
    public void negativeSignUpTestShortEmail() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutField("shortEmail");
        Assert.assertTrue(signUpPage.getErrorFromField(), "There is no error text at email field");
    }

    @Test(priority = 16, groups = "signup page", description = "Validate negative registration with short Password")
    public void negativeSignUpTestShortPassword() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutPasswordField("shortPassword");
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.getErrorField(Constants.expectedLength), "There is no error for short password");
    }

    @Test(priority = 17, groups = "signup page", description = "Validate negative registration without lower case Password")
    public void negativeSignUpTestWithoutLowerCasePassword() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutPasswordField("withoutLowerCasePassword");
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.getErrorField(Constants.expectedLowerCase), "There is no error for password without lower case");
    }

    @Test(priority = 18, groups = "signup page", description = "Validate negative registration without upper case Password")
    public void negativeSignUpTestWithoutUpperCasePassword() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutPasswordField("withoutUpperCasePassword");
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.getErrorField(Constants.expectedUpperCase), "There is no error for password without upper case");
    }

    @Test(priority = 19, groups = "signup page", description = "Validate negative registration without number Password")
    public void negativeSignUpTestWithoutNumberPassword() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutPasswordField("withoutNumberPassword");
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.getErrorField(Constants.expectedNumber), "There is no error for password without number");
    }

    @Test(priority = 20, groups = "signup page", description = "Validate negative registration without special character Password")
    public void negativeSignUpTestWithoutSpecialCharacterPassword() {
        signUpPage.navigateToSignUpPage(signUpURL);
        signUpPage.fillPositiveDetailsWithoutPasswordField("withoutSpecialCharacterPassword");
        signUpPage.clickStartFreeTrial();
        Assert.assertTrue(signUpPage.getErrorField(Constants.expectedSpecialChar), "There is no error for password without special character");
    }

    @Test(priority = 21, groups = "product page", description = "Validate sign In page test")
    public void openProductPageTest() {
        signUpPage.getProductPage(productURL);
        productPage.clickLogin();
        Assert.assertTrue(signInPage.validateLoginPage(), "Login page was not loaded");
        productPage.navigateBack();
        Assert.assertTrue(productPage.validateProductPage(), "product page was not open");
    }

    @Test(priority = 22, groups = "product page", description = "Validate description text of Vicarius")
    public void validateDescriptionTest() {
        Assert.assertTrue(productPage.validateCompanyDescription(), "description of the company is not right");
    }

    @Test(priority = 23, groups = "product page", description = "Validate links at main page of Vicarius")
    public void validateLinksTest() {
        Assert.assertTrue(productPage.validateLinksAreNotEmpty(), "there is broken links");
    }


}
