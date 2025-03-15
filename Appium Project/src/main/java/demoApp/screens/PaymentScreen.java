package demoApp.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.io.FileReader;
import java.time.Duration;

public class PaymentScreen {

    AndroidDriver driver;

    private final By checkoutHeader = AppiumBy.xpath("//android.widget.TextView[@text=\"Checkout\"]");

    private final By fullNameField = AppiumBy.xpath("//android.widget.EditText[@content-desc=\"Full Name* input field\"]");

    private final By cardNumberField = AppiumBy.accessibilityId("Card Number* input field");

    private final By expirationDateField = AppiumBy.accessibilityId("Expiration Date* input field");

    private final By securityCodeField = AppiumBy.accessibilityId("Security Code* input field");

    private final By reviewOrderBtn = AppiumBy.accessibilityId("Review Order button");

    public PaymentScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public PaymentScreen assertPaymentScreen(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(checkoutHeader).getText(),"Checkout");
        softAssert.assertAll();
        return this;
    }

    public ReviewOrderScreen fillPaymentInformation() throws Exception {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/test/resources/testData/paymentTestData.json");
        Object obj =jsonParser.parse(reader);

        JSONObject fullNameObj = (JSONObject) obj;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(fullNameField)));
        driver.findElement(fullNameField).sendKeys((String) fullNameObj.get("FullName"));

        JSONObject cardNumberObj = (JSONObject) obj;
        driver.findElement(cardNumberField).sendKeys((String) cardNumberObj.get("CardNumber"));

        JSONObject expirationDateObj = (JSONObject) obj;
        driver.findElement(expirationDateField).sendKeys((String) expirationDateObj.get("ExpirationDate"));

        JSONObject securityCodeObj = (JSONObject) obj;
        driver.findElement(securityCodeField).sendKeys((String) securityCodeObj.get("SecurityCode"));

        driver.findElement(reviewOrderBtn).click();

        return new ReviewOrderScreen (driver);
    }
}
