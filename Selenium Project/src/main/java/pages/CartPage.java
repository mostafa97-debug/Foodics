package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItems = By.className("a-truncate-cut]");
    private final By priceItem = By.className("sc-apex-cart-price");
    private final By cartPageButton = By.id("nav-cart-text-container");
    private final By checkoutButton = By.name("proceedToRetailCheckout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfItemsInCart() {
        return driver.findElements(cartItems).size();
    }

    public void openCartPage() {
        click(cartPageButton);
    }

    public double calulatePriceForAllItems() {
        double total = 0;
        try {
            // Locate all price elements using CSS selector
            List<WebElement> priceElements = driver.findElements(priceItem);

            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (WebElement element : priceElements) {
                // Extract text using JavaScript to handle &nbsp; correctly
                String priceText = (String) js.executeScript("return arguments[0].textContent;", element);

                // Clean the price text (remove "EGP " and commas)
                String cleanedPrice = priceText.replace("EGP\u00A0", "").replace(",", "");

                // Parse to double and add to total
                total += Double.parseDouble(cleanedPrice);
            }

            System.out.println("Total Price: EGP " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public void openCheckoutPage() {
        click(checkoutButton);
    }


}