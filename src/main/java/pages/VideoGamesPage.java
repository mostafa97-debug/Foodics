package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class VideoGamesPage extends BasePage {

    private final By freeShippingCheckBox = By.className("a-icon-checkbox");
    private final By newConditionCheckBox = By.xpath("//*[text()='New']");
    private final By sortDropdown = By.className("a-dropdown-label");
    private final By sortDropdownOption = By.xpath("//*[text()='Price: High to Low']");

    public VideoGamesPage(WebDriver driver) {
        super(driver);
    }

    public void applyFilters() throws InterruptedException {
        moveToElement(freeShippingCheckBox);
        toggleCheckbox(freeShippingCheckBox);
        click(newConditionCheckBox);
    }

    public void sortHighToLow() {
        click(sortDropdown);
        click(sortDropdownOption);
    }

    public void addProductsBelowPriceWithPagination(double price) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean itemsFound = false;
        int maxAttempts = 5; // Safety limit to prevent infinite loops
        int attempts = 0;

        try {

            while (!itemsFound && attempts < maxAttempts) {
                int added = processPage(price);

                if (added > 0) {
                    itemsFound = true;
                    break;
                }

                // Look for next page button
                WebElement pagination = driver.findElement(By.cssSelector("div.s-pagination-container"));
                WebElement nextButton = pagination.findElement(By.cssSelector("a.s-pagination-next"));

                if (nextButton.getAttribute("aria-disabled") != null &&
                        nextButton.getAttribute("aria-disabled").equals("true")) {
                    System.out.println("No more pages available");
                    break;
                }

                System.out.println("Moving to next page...");
                nextButton.click();
                wait.until(ExpectedConditions.urlContains("page=" + (++attempts + 1)));
            }
        } catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static int processPage(double price) {
        int count = 0;
        List<WebElement> products = driver.findElements(By.cssSelector("[data-component-type='s-search-result']"));

        for (WebElement product : products) {
                WebElement addToCartBtn = product.findElement(By.xpath(".//button[text()='Add to cart']"));
                WebElement priceElement = product.findElement(By.cssSelector(".a-price .a-offscreen"));

                String priceText = priceElement.getAttribute("innerText").replaceAll("[^\\d.]", "");
                double parseDouble = Double.parseDouble(priceText);
                System.out.println("Price: " + parseDouble);
                if (parseDouble < price) {
                    addToCartBtn.click();
                    System.out.println("Added item with price: " + parseDouble);
                    count++;
                }
        }
        return count;

    }

    public void addProductsBelowPriceWithoutPagination(double price2) {

        try {
            // Find all product items
            List<WebElement> products = driver.findElements(By.cssSelector("[data-component-type='s-search-result']"));

            for (WebElement product : products) {
                try {
                    // Check if "Add to Cart" button exists
                    WebElement addToCartBtn = product.findElement(By.xpath(".//button[contains(text(), 'Add to cart')]"));

                    // Find price element (adjust selector based on actual HTML structure)
                    WebElement priceElement = product.findElement(By.cssSelector(".a-price .a-offscreen"));
                    String priceText = priceElement.getAttribute("innerText").replaceAll("[^\\d.]", "");
                    double price = Double.parseDouble(priceText);
                    System.out.println("Price: " + price);
                    if (price < 30000) {
                        addToCartBtn.click();
                        System.out.println("Added item with price: " + price);
                        // Add wait if needed for confirmation
                    }
                } catch (Exception e) {
                    // Skip items without add to cart button or invalid price
                    continue;

                }
            }
        } finally {
            driver.quit();
        }
    }
}

