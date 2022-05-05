package commonAPI;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import extentreport.ExtentManager;
import extentreport.ExtentTestManager;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.apache.poi.xssf.usermodel.XSSFShapeGroup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

public class CommonAPI{

    public static ExtentReports extent;
    public static WebDriver driver;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }
    @BeforeMethod
    public void startExtent (Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    @AfterMethod
    public void afterEachMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }
        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS,"Test passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Test failed");
        } else {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
    }
    @AfterSuite
    public void generateReport() {
        extent.close();
    }
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
    @Parameters({"propertyDriver","driverPath"})
    @BeforeMethod
    public void driverSetup(String propertyDriver, String driverPath) {
        System.setProperty(propertyDriver,driverPath);
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void burnDown() {
        driver.quit();
    }

    public static String getCellStringValue(File testCases, int page, int row, int column) {
        try {
            FileInputStream input = new FileInputStream(testCases);
            XSSFWorkbook yourWorkBook = new XSSFWorkbook(input);
            XSSFSheet yourSheet = yourWorkBook.getSheetAt(page);
            return yourSheet.getRow(row).getCell(column).getStringCellValue();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        } catch (IOException e1) {
            System.out.println(e1.getCause());
            System.out.println(e1.getMessage());
        }
        return null;
    }
}
