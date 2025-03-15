package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By addressButton = By.xpath("//a[contains(@data-action,'address-select')]");
    private final By cashOnDeliveryOption = By.xpath("//input[@value='Cash on Delivery']");
    private final By placeOrderButton = By.name("placeYourOrder1");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void selectAddress() {
        click(addressButton);
    }

    public void selectCashOnDelivery() {
        click(cashOnDeliveryOption);
    }

    public void placeOrder() {
        click(placeOrderButton);
    }
}