package demoApp.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class CheckoutCompleteScreen {

    AndroidDriver driver;

    private final By checkoutComplete = AppiumBy.xpath("//android.widget.TextView[@text=\"Checkout Complete\"]");

    private final By continueShoppingBtn = AppiumBy.accessibilityId("Continue Shopping button");


    public CheckoutCompleteScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public CheckoutCompleteScreen assertCheckoutCompleteScreen(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(checkoutComplete).getText(),"Checkout Complete");
        softAssert.assertAll();
        return this;
    }

    public HomeScreen clickContinueShoppingBtn(){
        driver.findElement(continueShoppingBtn).click();
        return new HomeScreen(driver);
    }
}
