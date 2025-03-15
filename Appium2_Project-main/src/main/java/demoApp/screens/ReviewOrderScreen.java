package demoApp.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class ReviewOrderScreen {

    AndroidDriver driver;

    private final By reviewYourOrder = AppiumBy.xpath("//android.widget.TextView[@text=\"Review your order\"]");

    private final By placeOrderBtn = AppiumBy.accessibilityId("Place Order button");


    public ReviewOrderScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public ReviewOrderScreen assertReviewOrderScreen(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(reviewYourOrder).getText(),"Review your order");
        softAssert.assertAll();
        return this;
    }

    public CheckoutCompleteScreen clickOnPlaceOrderButton(){
    driver.findElement(placeOrderBtn).click();
    return new CheckoutCompleteScreen(driver);
    }

}
