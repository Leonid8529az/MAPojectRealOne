package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import screenshotscapture.ScreenShotCapture;

public class Listeners extends ScreenShotCapture implements ITestListener {

    public void onTestStart(ITestResult tr) {
        Reporter.log( " started");
        System.out.println( " started");
    }
    public void onTestSuccess(ITestResult tr) {
        Reporter.log("TestListeners passed");
        System.out.println("TestListeners passed");
    }
    public void onTestFailure(ITestResult tr) {
        Reporter.log("TestListeners failed");
        System.out.println("TestListeners failed");
        getScreenshot();

    }
    public void onTestSkipped(ITestResult tr) {
        Reporter.log("TestListeners skipped");
        System.out.println("TestListeners skipped");
    }

}
