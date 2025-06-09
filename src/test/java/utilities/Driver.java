package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Driver {

    private static WebDriver driver;
    private static String browser = Config.getValue("browser");
    private static String url = Config.getValue("url");
    private static String login = Config.getValue("username");
    private static String password = Config.getValue("password");

    public static WebDriver getDriver(){
        // if we don't have currently running browser
        // then create a new browser session
        if(driver == null){
            if(browser.equalsIgnoreCase("chrome")){
                driver = new ChromeDriver();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
            }
            else if(browser.equalsIgnoreCase("firefox")){
                driver = new FirefoxDriver();

                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
            } else if(browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-popup-blocking");
                edgeOptions.addArguments("--disable-notifications");
            } else{
                // open my default browser
                driver = new ChromeDriver();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
            }
            int pageLoadTimeOut = Integer.parseInt(Config.getValue("pageLoadTimeOut"));
            int implicitlyWait = Integer.parseInt(Config.getValue("implicitWait"));

            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeOut));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
            driver.manage().window().maximize();
            // when you need to open new browser
            return driver;
        }
        // if you have opened browser, it will just return it
        return driver;
    }

    public static void closeBrowser(){
        if(driver!= null){
            driver.quit();
            driver = null;
        }
    }
}
