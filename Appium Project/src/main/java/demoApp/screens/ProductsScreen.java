package demoApp.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class ProductsScreen {

    AndroidDriver driver;

    private final By counterPlusBtn = AppiumBy.accessibilityId("counter plus button");

    private final By addToCartBtn = AppiumBy.accessibilityId("Add To Cart button");

    private final By cartBadge = AppiumBy.accessibilityId("cart badge");

    private final By productHighlightsText = AppiumBy.xpath("//android.widget.TextView[@text=\"Product Highlights\"]");


    public ProductsScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public ProductsScreen assertProductsScreen(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(productHighlightsText).getText(),"Product Highlights");
        softAssert.assertAll();
        return this;
    }

    public ProductsScreen clickOnCounterButton(){
        for (int i=0 ; i < 2 ; i++){
            driver.findElement(counterPlusBtn).click();
        }
        return this;
    }

    public ProductsScreen clickOnAddToCartButton(){
        driver.findElement(addToCartBtn).click();
        return this;
    }

    public CartScreen clickOnCartBadge(){
        driver.findElement(cartBadge).click();
        return new CartScreen(driver);
    }

}
