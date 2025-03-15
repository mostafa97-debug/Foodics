package demoApp.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class CartScreen {

    AndroidDriver driver;

    private final By proceedToCheckoutBtn =AppiumBy.accessibilityId("Proceed To Checkout button");

    private final By myCartText = AppiumBy.xpath("//android.widget.TextView[@text=\"My Cart\"]");


    public CartScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public CartScreen assertCartScreen(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(myCartText).getText(),"My Cart");
        softAssert.assertAll();
        return this;
    }

    public CheckOutScreen clickOnProceedToCheckoutButton(){
        driver.findElement(proceedToCheckoutBtn).click();
        return new CheckOutScreen(driver);
    }
}
