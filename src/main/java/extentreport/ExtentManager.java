package extentreport;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static ITestContext context;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            File outputDirectory = new File(context.getOutputDirectory());
            File resultDirectory = new File(outputDirectory.getParentFile(),"html");
            extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/MyExtentReport.html", true);
            extent.addSystemInfo("Host name", "LocalHost");
            extent.addSystemInfo("Environment", "QA");
            extent.addSystemInfo("User name", "Leonid");

            extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
        }
        return extent;
    }

    public static void setOutputDirectory(ITestContext context) {
        ExtentManager.context = context;
    }
}
