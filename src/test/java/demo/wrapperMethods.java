package demo;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wrapperMethods {
    private WebDriver driver;
    private WebDriverWait wait;

    public wrapperMethods(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openHomePage(String url) {
        System.out.println("Opening homepage: " + url);
        driver.get(url);
    }

    public void searchForProduct(String productName, String textXpath, String searchClick ) {
        System.out.println("Searching for product: " + productName);
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textXpath)));
        searchBox.clear();
        searchBox.sendKeys(productName);
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchClick)));
        searchButton.click();
    }

    public void sortBy() {
        System.out.println("Sorting by: Popularity");
        WebElement sortElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Popularity']")));
        sortElement.click();
    }

    public int countItemsWithLowRating(double maxRating) {
        System.out.println("Counting items with rating less than or equal to " + maxRating + " stars");
        List<WebElement> ratings = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='XQDdHH']")));
        int count = 0;
        for (WebElement ratingElement : ratings) {
            double rating = Double.parseDouble(ratingElement.getText());
            if (rating <= maxRating) {
                count++;
            }
        }
        return count;
    }

    public void printItemsWithDiscount(int minDiscountPercentage) {
        System.out.println("Printing  the titles and discount % > than 17%: ");
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='tUxRFH']")));
        for (WebElement item : items) {
            WebElement titleElement = item.findElement(By.xpath(".//div[@class='KzDlHZ']"));
            String title = titleElement.getText();
            WebElement discountElement = item.findElement(By.xpath(".//div[@class='UkUFwK']"));
            String discountText = discountElement.getText().replaceAll("[^0-9]", "");
            int discount = Integer.parseInt(discountText);
            if (discount > minDiscountPercentage) {
                System.out.println("Title: " + title + ", Discount: " + discount + "%");
            }
        }
    }
   
    public void sortBy4StarRating() {
        System.out.println("Sorting products by 4 stars and above Ratings");
        WebElement reviewsSort = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='XqNaEv'])[1]")));
        reviewsSort.click();
    }

    public void printTop5Items() {
        System.out.println("Printing 5 Items with highest review counts: ");
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='slAVV4']")));
        products.sort((p1, p2) -> {
            WebElement reviewCountElement1 = p1.findElement(By.xpath(".//span[@class='Wphh3N']"));
            WebElement reviewCountElement2 = p2.findElement(By.xpath(".//span[@class='Wphh3N']"));
            int reviewCount1 = Integer.parseInt(reviewCountElement1.getText().replaceAll("[^0-9]", ""));
            int reviewCount2 = Integer.parseInt(reviewCountElement2.getText().replaceAll("[^0-9]", ""));
            return Integer.compare(reviewCount2, reviewCount1);
        });

        for (int i = 0; i < Math.min(products.size(), 5); i++) {
            WebElement product = products.get(i);
            WebElement title = product.findElement(By.xpath(".//a[@class='wjcEIp']"));
            WebElement image = product.findElement(By.xpath(".//img[@class='DByuf4']"));
            System.out.println("Title: " + title.getText());
            System.out.println("Image URL: " + image.getAttribute("src"));
            System.out.println();
        }
    }

   
}