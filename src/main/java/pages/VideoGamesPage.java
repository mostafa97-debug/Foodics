package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

    public void addProductsBelowPriceWithPagination(double maxPrice) {
        try {
            // Loop through the first 5 pages
            for (int pageNumber = 1; pageNumber <= 3; pageNumber++) {
                System.out.println("Processing page: " + pageNumber);

                // Find all product items on the current page
                List<WebElement> products = driver.findElements(By.cssSelector("[data-component-type='s-search-result']"));

                for (WebElement product : products) {
                    try {
                        // Check if "Add to Cart" button exists
                        WebElement addToCartBtn = product.findElement(By.xpath(".//button[text()='Add to cart']"));

                        // Find price element and parse price
                        WebElement priceElement = product.findElement(By.cssSelector(".a-price .a-offscreen"));
                        String priceText = priceElement.getAttribute("innerText").replaceAll("[^\\d.]", "");
                        double price = Double.parseDouble(priceText);

                        // Log price for debugging
                        System.out.println("Product Price: " + price);

                        // Add to cart if price is below threshold
                        if (price < maxPrice) {
                            addToCartBtn.click();
                            System.out.println("Added item with price: " + price);
                            // Add explicit wait if needed for confirmation
                        }
                    } catch (NoSuchElementException e) {
                        // Skip items without required elements
                        System.err.println("Skipping item: Missing 'Add to Cart' button or price.");
                    } catch (NumberFormatException e) {
                        // Handle invalid price format
                        System.err.println("Skipping item: Invalid price format.");
                    }
                }

                // Navigate to the next page if not on the last page
                if (pageNumber < 5) {
                    try {
                        // Locate the "Next" button or specific page link
                        WebElement nextPageButton = driver.findElement(By.xpath("//a[contains(@aria-label, 'Go to next page')]"));
                        nextPageButton.click();

                        // Add a wait to ensure the next page loads completely
                        Thread.sleep(2000); // Adjust sleep time as needed
                    } catch (NoSuchElementException e) {
                        System.err.println("No more pages available. Exiting pagination loop.");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    if (price > price2) {
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

