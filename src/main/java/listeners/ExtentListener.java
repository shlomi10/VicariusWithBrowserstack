package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportManager.ExtentManager;

/**
 * this class represents the ITest listener implementation
 *
 * @author Shlomi
 */

public class ExtentListener extends ExtentManager implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("The success test name is: " + result.getName());
        test.info("The test name: " + result.getMethod().getDescription());
        test.log(Status.PASS, result.getMethod().getDescription() + " succeeded");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        System.out.println("The failed test name is: " + result.getName());
        try {
            String picPath = captureScreen(driver);
            System.out.println("pic path: " + picPath);
            String[] split = picPath.split("/");
            test.fail(result.getTestName(), MediaEntityBuilder.createScreenCaptureFromPath(split[split.length - 1]).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.info("The test name: " + result.getMethod().getDescription());
        test.fail(result.getThrowable());
        test.fail(result.getMethod().getDescription() + " failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStart(ITestContext context) {
        init();

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}