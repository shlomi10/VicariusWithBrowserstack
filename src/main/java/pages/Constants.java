package pages;

import java.util.List;

/**
 * this class represents the constants strings
 *
 * @author Shlomi
 */

public class Constants {

    public static List<String> expectedErrorText = List.of("Validation failed", "Please, verify if all fields are correctly filled.");
    public static List<String> expectedFeatures = List.of("Vuln Discovery\nYou can’t fix what you can’t find.", "Vuln Prioritisation\nFocus on risks that have real potential for exploitation", "Vuln Remediation\nDon’t just find your flaws, fix them.", "Automation\nThreats don’t take time off, but you can.");
    public static List<String> expectedDescription = List.of("Vicarius develops an autonomous vulnerability remediation platform to help security teams protect their assets against software exploitation. Consolidating vulnerability assessment, prioritization, and remediation, Vicarius strengthens cyber hygiene and proactively reduces risk.");
    public static List<String> expectedLinks = List.of("https://www.vicarius.io/product", "https://www.vicarius.io/product/vulnerability-management", "https://www.vicarius.io/product/patch-management",
            "https://www.vicarius.io/product/patchless-protection", "https://www.vicarius.io/product/auto-actions", "https://www.vicarius.io/product/nmap", "https://www.vicarius.io/product/xtags",
            "https://www.vicarius.io/product/0-day-detection", "https://www.vicarius.io/solution", "https://www.vicarius.io/case-studies", "https://www.vicarius.io/research-center",
            "https://www.vicarius.io/research-center/support", "https://www.vicarius.io/webinars", "https://www.vicarius.io/blog", "https://www.vicarius.io/downloads", "https://www.vicarius.io/company",
            "https://www.vicarius.io/investors", "https://www.vicarius.io/partners", "https://www.vicarius.io/trust", "https://www.vicarius.io/careers", "https://www.vicarius.io/pricing",
            "https://www.vicarius.io/competitors/topia-vs-automox", "https://www.vicarius.io/competitors/topia-vs-manageengine", "https://www.vicarius.io/competitors/topia-vs-rapid7-insightvm",
            "https://www.vicarius.io/competitors/topia-vs-tenable-nessus", "https://www.vicarius.io/competitors/topia-vs-tanium",
            "https://www.vicarius.io/competitors/topia-vs-rmms", "https://www.vicarius.io/competitors/topia-vs-vulcan",
            "https://www.vicarius.io/competitors/topia-vs-pdq");
    public static String expectedFirstName = "test";
    public static String expectedLastname = "test";
    public static String expectedWorkEmail = "abd@thank.you";
    public static String expectedCompany = "dontAsk";
    public static String expectedPassword = "Password1@";
    public static String expectedConfirmPassword = "Password1@";
    public static List<String> expectedHoorayText = List.of("Hooray!", "Please check your email for verification link");
    public static String errorMissingFieldText = "Let’s try again...\nPlease review your login details and try again.";
    public static String expectedLength = "8 - 30 characters";
    public static String expectedLowerCase = "Lowercase letter";
    public static String expectedUpperCase = "Uppercase letter";
    public static String expectedNumber = "Number";
    public static String expectedSpecialChar = "Special character";

    public static void setMissingField(String missingField) {
        switch (missingField) {
            case "firstName":
                expectedFirstName = "";
                break;
            case "lastName":
                expectedLastname = "";
                expectedFirstName = "test";
                break;
            case "email":
                expectedWorkEmail = "";
                expectedLastname = "test";
                break;
            case "shortEmail":
                expectedWorkEmail = "12@yes";
                expectedLastname = "test";
                break;
            case "invalidEmail":
                expectedWorkEmail = "asb@@abc.com";
                expectedLastname = "test";
                break;
            case "company":
                expectedCompany = "";
                expectedWorkEmail = "abd@thank.you";
                break;
            case "password":
                expectedPassword = "";
                expectedCompany = "dontAsk";
                break;
            case "shortPassword":
                expectedPassword = "12Aa@";
                expectedWorkEmail = "asb@abc.com";
                break;
            case "withoutLowerCasePassword":
                expectedPassword = "PASSWORD1@";
                expectedCompany = "dontAsk";
                break;
            case "withoutUpperCasePassword":
                expectedPassword = "password1!";
                expectedCompany = "dontAsk";
                break;
            case "withoutNumberPassword":
                expectedPassword = "Password!";
                expectedCompany = "dontAsk";
                break;
            case "withoutSpecialCharacterPassword":
                expectedPassword = "Password1";
                expectedCompany = "dontAsk";
                break;
            case "confirmationPassword":
                expectedConfirmPassword = "";
                expectedPassword = "Password1@";
                break;
        }
    }
}
