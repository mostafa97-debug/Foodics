package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import utils.DriverManager;

public class AmazonTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private VideoGamesPage videoGamesPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        videoGamesPage = new VideoGamesPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void testAmazonScenario() throws InterruptedException {
        driver.get("https://www.amazon.eg/s?language=en");
        loginPage.login("conanstud@gmail.com", "Asd123!@#");

        homePage.selectVideoGamesCategory();

        videoGamesPage.applyFilters();
        videoGamesPage.sortHighToLow();
        videoGamesPage.addProductsBelowPriceWithPagination(15000);
        cartPage.openCartPage();
        cartPage.calulatePriceForAllItems();
        Assert.assertTrue(cartPage.getNumberOfItemsInCart() > 0, "Cart is empty!");
        cartPage.openCheckoutPage();

//        double totalPrice = cartPage.getTotalPrice();
//
//        checkoutPage.selectAddress();
//        checkoutPage.selectCashOnDelivery();
//        checkoutPage.placeOrder();
//
//        Assert.assertTrue(totalPrice > 0, "Total price is invalid!");
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}