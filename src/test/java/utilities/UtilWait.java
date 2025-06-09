package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class UtilWait {

    public static WebElement waitUntilElementIsClickable (WebDriver driver,
                                                          int seconds,
                                                          By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    public static WebElement waitUntilElementIsClickable (WebDriver driver,
                                                          int seconds,
                                                          WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static void takeScreenshot(WebDriver driver,String pathname) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(pathname));
    }

    public static String generateString(String characters, int length)
    {
        Random rnd = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(text);
    }


}
