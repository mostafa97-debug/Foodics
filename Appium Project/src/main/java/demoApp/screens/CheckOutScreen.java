package demoApp.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;

public class CheckOutScreen {

    AndroidDriver driver;

    private final By fullNameInputField = AppiumBy.accessibilityId("Full Name* input field");

    private final By addressLine1Field = AppiumBy.accessibilityId("Address Line 1* input field");

    private final By addressLine2Field = AppiumBy.accessibilityId("Address Line 2 input field");

    private final By cityField = AppiumBy.accessibilityId("City* input field");

    private final By stateField = AppiumBy.accessibilityId("State/Region input field");

    private final By zipCodeField = AppiumBy.accessibilityId("Zip Code* input field");

    private final By countryField = AppiumBy.accessibilityId("Country* input field");

    private final By toPaymentBtn = AppiumBy.accessibilityId("To Payment button");

    private final By checkoutText = AppiumBy.xpath("//android.widget.TextView[@text=\"Checkout\"]");



    public CheckOutScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public CheckOutScreen assertCheckOutScreen(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(checkoutText).getText(),"Checkout");
        softAssert.assertAll();
        return this;
    }

    public PaymentScreen fillTheCheckoutInformation() throws Exception {
        JSONParser jsonParser =new JSONParser();
        FileReader reader =new FileReader("src/test/resources/testData/checkoutTestData.json");
        Object obj =jsonParser.parse(reader);

        JSONObject fullNameObj = (JSONObject) obj;
        driver.findElement(fullNameInputField).sendKeys((String) fullNameObj.get("FullName"));

        JSONObject addressLine1Obj = (JSONObject) obj;
        driver.findElement(addressLine1Field).sendKeys((String) addressLine1Obj.get("AddressLine1"));

        JSONObject addressLine2Obj = (JSONObject) obj;
        driver.findElement(addressLine2Field).sendKeys((String) addressLine2Obj.get("AddressLine2"));

        JSONObject cityObj = (JSONObject) obj;
        driver.findElement(cityField).sendKeys((String) cityObj.get("City"));

        JSONObject stateObj = (JSONObject) obj;
        driver.findElement(stateField).sendKeys((String) stateObj.get("State"));

        JSONObject zipCodeObj = (JSONObject) obj;
        driver.findElement(zipCodeField).sendKeys((String) zipCodeObj.get("ZipCode"));

        JSONObject countryObj = (JSONObject) obj;
        driver.findElement(countryField).sendKeys((String) countryObj.get("Country"));

        driver.findElement(toPaymentBtn).click();

        return new PaymentScreen (driver);
    }

}
