package com.demoAppTest;

import demoApp.screens.HomeScreen;
import jdk.jfr.Description;
import org.testng.annotations.Test;


public class DemoAppTest extends SetUP_Test{

    @Description
            (
                    "Given user open Sauce labs mobile application"+
                            "When click on Menu"+
                            "And click on Login"+
                            "And login Using Valid Email & Password"+
                            "And choose Product"+
                            "And click on counter Plus Button"+
                            "And click on add To Cart button"+
                            "And click on Cart Badge"+
                            "And click on Proceed To Checkout Button"+
                            "And fill Checkout Information"+
                            "And fill Payment Information"+
                            "And click on place order button"+
                            "And click on continue shopping button"+
                            "Then user should return to Home screen"
            )


    @Test(description = "Executing E2E ordering item scenario")

    public void CheckOutOrderTest() throws Exception {
        new HomeScreen(driver).assertHomeScreen()
                .clickOnOpenMenu()
                .assertHomeScreen()
                .clickOnLoginButton()
                .assertLoginScreen()
                .loginWithValidEmailAndPassword()
                .clickOnProduct()
                .assertProductsScreen()
                .clickOnCounterButton()
                .clickOnAddToCartButton()
                .clickOnCartBadge()
                .assertCartScreen()
                .clickOnProceedToCheckoutButton()
                .assertCheckOutScreen()
                .fillTheCheckoutInformation()
                .assertPaymentScreen()
                .fillPaymentInformation()
                .assertReviewOrderScreen()
                .clickOnPlaceOrderButton()
                .assertCheckoutCompleteScreen()
                .clickContinueShoppingBtn()
                .assertHomeScreen();
    }
}
