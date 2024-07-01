package demo;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;

import javax.swing.text.html.Option;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
        ChromeDriver driver;
        public TestCases()
        {
            System.out.println("Constructor: TestCases");
 
            WebDriverManager.chromedriver().timeout(30).setup();
            ChromeOptions options = new ChromeOptions();
            LoggingPreferences logs = new LoggingPreferences();
     
            // Set log level and type
            logs.enable(LogType.BROWSER, Level.INFO);
            logs.enable(LogType.DRIVER, Level.ALL);
            options.setCapability("goog:loggingPrefs", logs);
     
            // Connect to the chrome-window running on debugging port
            options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
     
            // Set path for log file
            System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");
     
            driver = new ChromeDriver(options);
     
            // Set browser to maximize and wait
            driver.manage().window().maximize();   
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


    
        }
    
        public void endTest()
        {
            System.out.println("End Test: TestCases");
            driver.close();
            driver.quit();
    
        }
    

    
        public  void testCase01(){
            System.out.println("Start Test case: testCase01");
            driver.get("https://www.google.com");
            System.out.println("end Test case: testCase01");
        }
    }
            