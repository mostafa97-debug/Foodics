package demoApp.screens;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;



public class LoginScreen {

    AndroidDriver driver;

    private final By userNameLocator = AppiumBy.accessibilityId("Username input field");

    private final By passwordLocator = AppiumBy.accessibilityId("Password input field");

    private final By loginBtn = AppiumBy.accessibilityId("Login button");

    private final By loginText = AppiumBy.xpath("(//android.widget.TextView[@text=\"Login\"])[1]");


    public LoginScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public LoginScreen assertLoginScreen(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(loginText).getText(),"Login");
        softAssert.assertAll();
        return this;
    }

    public HomeScreen loginWithValidEmailAndPassword() throws Exception {

        JSONParser jsonParser = new JSONParser();
        FileReader reader =new FileReader(System.getProperty("user.dir")+"/src/test/resources/testData/loginTestData.json");

        Object obj = jsonParser.parse(reader);

        JSONObject emailObj = (JSONObject) obj;
        driver.findElement(userNameLocator).sendKeys((String) emailObj.get("Email"));

        JSONObject passwordObj = (JSONObject) obj;
        driver.findElement(passwordLocator).sendKeys(((String) passwordObj.get("Password")));

        driver.findElement(loginBtn).click();

        return new HomeScreen(driver);
    }

}
