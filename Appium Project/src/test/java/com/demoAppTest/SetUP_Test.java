package com.demoAppTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SetUP_Test {

   public AndroidDriver driver;
    AppiumDriverLocalService service;
    UiAutomator2Options option;

    @BeforeSuite
    public void runServer() {
        service = AppiumDriverLocalService.buildDefaultService();
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\AlBadr\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withTimeout(Duration.ofSeconds(120)).withIPAddress("0.0.0.0").usingPort(4723)
                .build();
        service.start();
    }

    @BeforeClass
    public void runApp(){
        option =new UiAutomator2Options();
        option.setPlatformName(System.getProperty("platformName"));
        option.setDeviceName(System.getProperty("deviceName"));
        option.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        option.setAppActivity(System.getProperty("androidAppActivity"));
        option.setAppPackage(System.getProperty("androidAppPackage"));
        option.setApp(System.getProperty("user.dir")+"/src/test/resources/app/Android-MyDemoAppRN.1.3.0.build-244.apk");
    }

    @BeforeMethod
    public void androidDriver() throws MalformedURLException {
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void quitAndroidDriver() {
        driver.quit();
    }

    @AfterSuite
    public void stopServer() {
        service.stop();
    }
}
