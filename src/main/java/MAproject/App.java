package MAproject;


import com.fasterxml.jackson.databind.ObjectMapper;
import commonAPI.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class App extends CommonAPI {
        public static void main(String[] args) {
                File file = new File("C:\\Users\\wormi\\IdeaProjects\\MAproject\\testcases\\TestCaseTemplet.xlsx");
                String hello = CommonAPI.getCellStringValue(file, 0, 3, 0);
                System.out.println(hello);
        }
}
