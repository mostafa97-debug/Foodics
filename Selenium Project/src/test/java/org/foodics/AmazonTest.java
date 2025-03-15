package org.foodics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.DriverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

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
    public void testAmazonScenario() throws InterruptedException, FileNotFoundException {
        driver.get("https://www.amazon.eg/s?language=en");

        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/org/foodics/credentials.properties")) {
            properties.load(input);

            // Read username and password from the properties file
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            loginPage.login(username, password);
            homePage.selectVideoGamesCategory();

            videoGamesPage.applyFilters();
            videoGamesPage.sortHighToLow();
            videoGamesPage.addProductsBelowPriceWithPagination(15000);
            cartPage.openCartPage();
            cartPage.calulatePriceForAllItems();
            Assert.assertTrue(cartPage.getNumberOfItemsInCart() > 0, "Cart is empty!");
            cartPage.openCheckoutPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}