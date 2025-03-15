package demoApp.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class HomeScreen {

    AndroidDriver driver;

    private final By openMenu = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"open menu\"]");

    private final By loginButton = AppiumBy.accessibilityId("menu item log in");

    private final By product3 = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"store item\"])[3]");

    private final By productsText = AppiumBy.xpath("//android.widget.TextView[@text=\"Products\"]");


    public HomeScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public HomeScreen assertHomeScreen(){
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals(driver.findElement(productsText).getText(),"Products");
        softAssert.assertAll();
        return this;
    }

    public HomeScreen clickOnOpenMenu(){
    driver.findElement(openMenu).click();
    return this;
    }

    public LoginScreen clickOnLoginButton(){
    driver.findElement(loginButton).click();
    return new LoginScreen(driver);
    }

    public ProductsScreen clickOnProduct(){
    driver.findElement(product3).click();
    return new ProductsScreen(driver);
    }
}
