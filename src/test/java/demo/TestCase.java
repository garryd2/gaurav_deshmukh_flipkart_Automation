package demo;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {
    private WebDriver driver;
    private WebDriverWait wait;
    private wrapperMethods seleniumWrapper;

    @BeforeSuite(alwaysRun = true)
    public void startTest() {
        System.out.println("TestCases Started:");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        seleniumWrapper = new wrapperMethods(driver, wait);
    }

    @Test(priority = 1, enabled = true)
    public void testCase_01() throws InterruptedException {
        System.out.println("Test Case 1 Started");
        try {
            seleniumWrapper.openHomePage("https://www.flipkart.com/");

            seleniumWrapper.searchForProduct("Washing Machine", "//input[@class='Pke_EE']",
                    "//button[@class='_2iLD__']");
            seleniumWrapper.sortBy();
            Thread.sleep(2000);
            int count = seleniumWrapper.countItemsWithLowRating(4.0);
            Thread.sleep(2000);
            System.out.println("Number of items with rating less than or equal to 4 stars: " + count);
        } catch (Exception e) {
            System.out.println("An unexpected exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Test Case 1 Ended");
    }

    @Test(priority = 2, enabled = true)
    public void testCase_02() throws InterruptedException {
        System.out.println("Test Case 2 Started");
        try {
            seleniumWrapper.searchForProduct("iPhone", "//input[@class='zDPmFV']", "//button[@class='MJG8Up']");
            Thread.sleep(3000);
            seleniumWrapper.printItemsWithDiscount(17);
        } catch (Exception e) {
            System.out.println("An unexpected exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Test Case 2 Ended");
    }

    @Test(priority = 3, enabled = true)
    public void testCase_03() throws InterruptedException {
        System.out.println("Test Case 3 Started");
        try {
            seleniumWrapper.searchForProduct("Coffee Mug", "//input[@class='zDPmFV']", "//button[@class='MJG8Up']");
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300);");
            Thread.sleep(2000); 
            seleniumWrapper.sortBy4StarRating();
            Thread.sleep(3000);
            seleniumWrapper.printTop5Items();
        } catch (StaleElementReferenceException exception ) {
            System.out.println("An unexpected exception occurred: " + exception.getMessage());
            exception.printStackTrace();
        }
        System.out.println("Test Case 3 Ended");
        Thread.sleep(3000);
    }

    @AfterSuite(alwaysRun = true)
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();
    }
}