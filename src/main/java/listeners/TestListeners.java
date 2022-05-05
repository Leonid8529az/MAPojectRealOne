package listeners;

import commonAPI.CommonAPI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class TestListeners extends CommonAPI {


    //File testCases = new File("C:\\Users\\wormi\\IdeaProjects\\MAproject\\testcases\\TestCaseTemplet.xlsx");


    @DataProvider(name = "accounts")
    public static Object[][] accounts() {
        return new Object[][] {{"hello","hello"},{"buy","buy"}};
    }

    @Test(dataProvider = "accounts")
    public void test1(String username, String password) {
        driver.get("https://dota2.ru/");
        driver.findElement(By.id("login_credential_form")).sendKeys(username);
        driver.findElement(By.id("login_password_form")).sendKeys(password);
        driver.findElement(By.xpath("(//i[@class='fas fa-sign-in-alt'])[2]")).click();
        System.out.println("Test one is being executed");
        Assert.assertTrue(true);
    }
    @Test
    public void test2() {
        driver.get("https://www.google.com/");
        System.out.println("Test two is being executed");
        Assert.assertTrue(true);
    }
}
