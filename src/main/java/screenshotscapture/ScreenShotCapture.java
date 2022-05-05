package screenshotscapture;

import commonAPI.CommonAPI;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenShotCapture extends CommonAPI {

    public void getScreenshot() {
        try {
            Date currentDate = new Date();
            String screenShotFileName = currentDate.toString().replace(" ", "-").replace(":","-");
            File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShotFile,new File (".//screenshots//" + screenShotFileName + ".png"));
        } catch (IOException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}
